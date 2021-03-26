package com.Wolfaton.game;

import static org.lwjgl.opengl.GL30C.*;

public class ShaderProgram {

    private final int objectId;

    public ShaderProgram() throws Exception {
        objectId = glCreateProgram();
        if (objectId == 0) {
            throw new Exception("Could not create Shader");
        }
    }

    public void attachShader(Shader shader) {
        glAttachShader(objectId, shader.getId());
    }

    public int getAttributeLocation(CharSequence name) {
        return glGetAttribLocation(objectId, name);
    }

    public void enableVertexAttribute(int location) {
        glEnableVertexAttribArray(location);
    }

    public void disableVertexAttribute(int location) {
        glDisableVertexAttribArray(location);
    }

    public void pointVertexAttribute(int location, int size, int stride, int offset) {
        glVertexAttribPointer(location, size, GL_FLOAT, false, stride, offset);
    }

    public int getUniformLocation(CharSequence name) {
        return glGetUniformLocation(objectId, name);
    }

    public void link() throws Exception {
        glLinkProgram(objectId);
        checkStatus();
    }

    public void use() {
        glUseProgram(objectId);
    }

    public void bindFragmentDataLocation(int number, CharSequence name) {
        glBindFragDataLocation(objectId, number, name);
    }

    public void unUse() {
        glUseProgram(0);
    }

    public void checkStatus() {
        int status = glGetProgrami(objectId, GL_LINK_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetProgramInfoLog(objectId));
        }
    }

    public void cleanup() {
        unUse();
        if (objectId != 0) {
            glDeleteProgram(objectId);
        }
    }
}
