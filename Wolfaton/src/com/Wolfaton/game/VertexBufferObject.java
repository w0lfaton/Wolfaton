package com.Wolfaton.game;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL30C.*;

public class VertexBufferObject {
    private final int objectId;

    public VertexBufferObject() {
        objectId = glGenBuffers();
    }

    public void bind(int target) {
        glBindBuffer(target, objectId);
    }

    public void uploadData(int target, FloatBuffer data, int usage) {
        glBufferData(target, data, usage);
    }

    public void delete() {
        glDeleteBuffers(objectId);
    }

    public int getObjectId() {
        return objectId;
    }
}
