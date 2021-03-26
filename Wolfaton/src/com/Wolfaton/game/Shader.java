package com.Wolfaton.game;

import java.io.*;

import static org.lwjgl.opengl.GL30C.*;

public class Shader {
    private final int objectId;

    public Shader(int type) {
        objectId = glCreateShader(type);
    }

    public int getObjectId() {
        return objectId;
    }

    public void delete() {
        glDeleteShader(objectId);
    }

    public void source(CharSequence source) {
        glShaderSource(objectId, source);
    }

    public void compile() {
        glCompileShader(objectId);
        checkStatus();
    }

    private void checkStatus() {
        int status = glGetShaderi(objectId, GL_COMPILE_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetShaderInfoLog(objectId));
        }
    }

    public static Shader createShader(int type, CharSequence source) {
        Shader shader = new Shader(type);
        shader.source(source);
        shader.compile();

        return shader;
    }

    public static Shader loadShader(int type, String path) {
        StringBuilder builder = new StringBuilder();

        try (InputStream in = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load a shader file!"
                    + System.lineSeparator() + ex.getMessage());
        }
        CharSequence source = builder.toString();

        return createShader(type, source);
    }
}
