package com.Wolfaton.game;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL30C.*;

public class ShaderProgram {

    private final int objectId;

    public ShaderProgram() throws Exception {
        objectId = glCreateProgram();
        if (objectId == 0) {
            throw new Exception("Could not create Shader");
        }
    }

    public int getObjectId() {
        return objectId;
    }

    public void attachShader(Shader shader) {
        glAttachShader(objectId, shader.getObjectId());
    }

    public int getAttributeLocation(CharSequence name) {
        return glGetAttribLocation(objectId, name);
    }

    public void enableVertexAttribute(int location) {
        glEnableVertexAttribArray(location);
    }

    public void pointVertexAttribute(int location, int size, int stride, int offset) {
        glVertexAttribPointer(location, size, GL_FLOAT, false, stride, offset);
    }

    public void disableVertexAttribute(int location) {
        glDisableVertexAttribArray(location);
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

    public int getUniformLocation(CharSequence name) {
        return glGetUniformLocation(objectId, name);
    }

    public void setUniform(int location, int value) {
        glUniform1i(location, value);
    }

    public void setUniform(int location, Vector4 value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(4);
            value.toBuffer(buffer);
            glUniform4fv(location, buffer);
        }
    }

    public void setUniform(int location, Matrix4 value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(4 * 4);
            value.toBuffer(buffer);
            glUniformMatrix4fv(location, false, buffer);
        }
    }

    public void cleanup() {
        unUse();
        if (objectId != 0) {
            glDeleteProgram(objectId);
        }
    }
}
