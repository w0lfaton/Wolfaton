package com.Wolfaton.game;

public interface GameContainerInterface {

    void init() throws Exception;

    void input(Window window);

    void update(float interval);

    void render(Window window);

    void cleanup();

}
