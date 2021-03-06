package com.Wolfaton.game;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30C.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Renderer {
    private VertexArrayObject vao;
    private VertexBufferObject vbo;
    private Shader vertexShader;
    private Shader fragmentShader;
    private ShaderProgram shaderProgram;
    private int uniModel;

    public void init() throws Exception {
        // Check this before running
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        // -- ABOVE --

        if (GameEngine.isDefaultContext()) {
            /* Generate Vertex Array Object */
            vao = new VertexArrayObject();
            vao.bind();
        } else {
            vao = null;
        }

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

        vertexShader = Shader.loadShader(GL_VERTEX_SHADER,"Wolfaton/vertex.vs");
        fragmentShader = Shader.loadShader(GL_FRAGMENT_SHADER,"Wolfaton/fragment.vs");

        shaderProgram = new ShaderProgram();
        shaderProgram.attachShader(vertexShader);
        shaderProgram.attachShader(fragmentShader);
        shaderProgram.bindFragmentDataLocation(0, "fragColor");
        shaderProgram.link();
        shaderProgram.use();

        specifyVertexAttributes();

        uniModel = shaderProgram.getAttributeLocation("Model");

        Matrix4 view = new Matrix4();
        int uniView = shaderProgram.getUniformLocation("view");
        shaderProgram.setUniform(uniView, view);

        float ratio;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            long window = glfwGetCurrentContext();
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            glfwGetFramebufferSize(window, width, height);
            ratio = width.get() / (float) height.get();
        }

        Matrix4 projection = Matrix4.orthographicMatrix(-ratio, ratio, -1f, 1f, -1f, 1f);
        int uniProjection = shaderProgram.getUniformLocation("projection");
        shaderProgram.setUniform(uniProjection, projection);
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    private void specifyVertexAttributes() {
        /* Specify Vertex Pointer */
        int posAttrib = shaderProgram.getAttributeLocation("position");
        shaderProgram.enableVertexAttribute(posAttrib);
        shaderProgram.pointVertexAttribute(posAttrib, 3, 6 * Float.BYTES, 0);

        /* Specify Color Pointer */
        int colAttrib = shaderProgram.getAttributeLocation("color");
        shaderProgram.enableVertexAttribute(colAttrib);
        shaderProgram.pointVertexAttribute(colAttrib, 3, 6 * Float.BYTES, 3 * Float.BYTES);
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT);

        vbo.bind(GL_ARRAY_BUFFER);
        specifyVertexAttributes();
        shaderProgram.use();

        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    public void update() {

    }

    public void cleanup() {
        vbo.delete();
        vertexShader.delete();
        fragmentShader.delete();
        vao.delete();
        shaderProgram.cleanup();
    }
}
