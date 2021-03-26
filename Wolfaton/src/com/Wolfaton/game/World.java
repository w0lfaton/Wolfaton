package com.Wolfaton.game;

import static org.lwjgl.opengl.GL30C.*;

public class World implements GameContainerInterface {
    private final Renderer renderer;

    public World() {
        renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        renderer.init();
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update(float interval) {

    }

    @Override
    public void render(Window window) {

    }
}
