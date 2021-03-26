package com.Wolfaton.game;

import static org.lwjgl.opengl.GL30C.*;

public class VertexArrayObject {
    private final int objectId;

    public VertexArrayObject(int objectId) {
        this.objectId = objectId;
    }

    public void bind() {
        glBindVertexArray(objectId);
    }

    public void delete() {
        glDeleteVertexArrays(objectId);
    }

    public int getObjectId() {
        return objectId;
    }
}
