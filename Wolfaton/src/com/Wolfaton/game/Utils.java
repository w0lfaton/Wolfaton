package com.Wolfaton.game;

import static org.lwjgl.opengl.GL11.*;

public class Utils {
    private Utils instance = new Utils();
    private Utils() {

    }

    public Utils getInstance() {
        return instance;
    }

    public void loadResource(String path) {
    }
}
