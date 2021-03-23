package com.Wolfaton.game;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    private ShaderObject shaderObject;

    public void init() throws Exception {
        shaderObject = new ShaderObject();
        shaderObject.createVertexShader(Utils.loadResource("/vertex.vs"));
        shaderObject.createFragmentShader(Utils.loadResource("/fragment.fs"));
        shaderObject.link();
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
