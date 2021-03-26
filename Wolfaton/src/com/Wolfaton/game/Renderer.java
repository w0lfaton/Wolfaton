package com.Wolfaton.game;

import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30C.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Renderer {
    private VertexArrayObject vao;
    private VertexBufferObject vbo;
    private Shader vertexShader;
    private Shader fragmentShader;
    private ShaderProgram shaderProgram;

    public void init() throws Exception {
        // Check this before running
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        //long window = glfwCreateWindow(width, height, title, NULL, NULL);
        // -- ABOVE --

        vao = new VertexArrayObject(glGenVertexArrays());
        vao.bind();

        try (MemoryStack stack = MemoryStack.stackPush()) {
            /* Vertex data */
            FloatBuffer vertices = stack.mallocFloat(3 * 6);
            vertices.put(-0.6f).put(-0.4f).put(0f).put(1f).put(0f).put(0f);
            vertices.put(0.6f).put(-0.4f).put(0f).put(0f).put(1f).put(0f);
            vertices.put(0f).put(0.6f).put(0f).put(0f).put(0f).put(1f);
            vertices.flip();

            /* Generate Vertex Buffer Object */
            vbo = new VertexBufferObject();
            vbo.bind(GL_ARRAY_BUFFER);
            vbo.uploadData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);


            //MemoryStack.stackPop();
        }

        shaderProgram = new ShaderProgram();
        vertexShader = Shader.loadShader(GL_VERTEX_SHADER,"/vertex.vs");
        fragmentShader = Shader.loadShader(GL_FRAGMENT_SHADER,"/vertex.vs");

        shaderProgram.attachShader(vertexShader);
        shaderProgram.attachShader(fragmentShader);
        shaderProgram.link();
        shaderProgram.use();
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
