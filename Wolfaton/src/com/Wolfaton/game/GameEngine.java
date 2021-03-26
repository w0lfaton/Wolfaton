package com.Wolfaton.game;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30C.*;

public class GameEngine implements Runnable {
    private Renderer renderer;
    private Window window;
    private final Thread gameLoopThread;
    private final GameContainerInterface gameContainer;
    private Timer timer;

    public GameEngine(String windowTitle, int width, int height, boolean vsSync, GameContainerInterface gameContainer) throws Exception {
        gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
        renderer = new Renderer();
        window = new Window(windowTitle, width, height, vsSync);
        this.gameContainer = gameContainer;
        timer = new Timer();
        init();
    }

    public void start() {
        gameLoopThread.start();
    }

    private void init() throws Exception {
        timer.init();
        renderer.init();
        gameContainer.init();
    }

    private void gameLoop() {
        clear();
        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window.getWindowHandle())) {
            float delta = timer.getDelta();
            this.clear(); // clear the framebuffer

            glfwSwapBuffers(window.getWindowHandle()); // swap the color buffers
            input();
            update(delta);
            render();
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
            timer.update();
        }
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    protected void input() {
        gameContainer.input(window);
    }

    protected void update(float interval) {
        gameContainer.update(interval);
        timer.updateUPS();
    }

    protected void render() {
        gameContainer.render(window);
        timer.updateFPS();
    }

    protected void cleanup() {
        gameContainer.cleanup();
        renderer.cleanup();
        window.cleanup();
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        } finally {
            cleanup();
        }
    }

    public Timer getTimer() {
        return timer;
    }

    public class Timer {
        private int fps;
        private int fpsCount;
        private int ups;
        private int upsCount;
        private double lastLoopTime;
        private float timer;

        public void init() {
            lastLoopTime = getTime();
        }

        public float getDelta() {
            double time = getTime();
            float delta = (float) (time - lastLoopTime);
            lastLoopTime = time;
            timer += delta;
            return delta;
        }

        public void update() {
            if (timer > 1f) {
                fps = fpsCount;
                fpsCount = 0;

                ups = upsCount;
                upsCount = 0;
                timer -= 1f;
            }
        }

        public void updateFPS() {
            fpsCount++;
        }

        public void updateUPS() {
            upsCount++;
        }

        public double getTime() {
            return glfwGetTime();
        }

        public double getLastLoopTime() {
            return lastLoopTime;
        }

        public int getFps() {
            return fps;
        }

        public int getUps() {
            return ups;
        }
    }

    public static boolean isDefaultContext() {
        return GL.getCapabilities().OpenGL32;
    }
}
