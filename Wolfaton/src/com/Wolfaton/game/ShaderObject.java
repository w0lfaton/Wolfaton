package com.Wolfaton.game;

import static org.lwjgl.opengl.GL20C.*;

public class ShaderObject {

    private final int objectId;

    private int vertexShaderId;

    private int fragmentShaderId;

    public ShaderObject() throws Exception {
        objectId = glCreateProgram();
        if (objectId == 0) {
            throw new Exception("Could not create Shader");
        }
    }

    public void createVertexShader(String shaderCode) throws Exception {
        vertexShaderId = createShader(shaderCode, GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String shaderCode) throws Exception {
        fragmentShaderId = createShader(shaderCode, GL_FRAGMENT_SHADER);
    }

    protected int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderId = glCreateShader(shaderType);
        if (shaderId == 0) {
            throw new Exception("Error creating shader. Type: " + shaderType);
        }

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(objectId, shaderId);

        return shaderId;
    }

    public void link() throws Exception {
        glLinkProgram(objectId);
        if (glGetProgrami(objectId, GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking Shader code: " + glGetProgramInfoLog(objectId, 1024));
        }

        if (vertexShaderId != 0) {
            glDetachShader(objectId, vertexShaderId);
        }
        if (fragmentShaderId != 0) {
            glDetachShader(objectId, fragmentShaderId);
        }

        glValidateProgram(objectId);
        if (glGetProgrami(objectId, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(objectId, 1024));
        }

    }

    public void bind() {
        glUseProgram(objectId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        if (objectId != 0) {
            glDeleteProgram(objectId);
        }
    }
}
