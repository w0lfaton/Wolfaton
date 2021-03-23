package com.Wolfaton.game;

public class Wolfaton implements GameContainerInterface {
    private final Renderer renderer;

    public Wolfaton() {
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
