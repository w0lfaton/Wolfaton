package com.Wolfaton.game;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30C.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.MemoryStack.*;

public class Window {
    private long windowHandle;
    private String title;
    private int width;
    private int height;
    private boolean vSync;
    private boolean isResized;
    private static GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);
    public Window(String title, int width, int height, boolean vSync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
        init();
    }

    public long getWindowHandle() {
        return windowHandle;
    }

    private void setResized(boolean b) {
        this.isResized = b;
    }

    private void init() {
        // Setup an error callback. Current implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback);

        // Initialize GLFW. Most GLFW functions will not work before doing this - good to know, aye.
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        windowHandle = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (windowHandle == NULL ) throw new RuntimeException("Failed to create the GLFW window");

        glfwSetFramebufferSizeCallback(windowHandle, (window, width, height) -> {
            Window.this.width = width;
            Window.this.height = height;
            Window.this.setResized(true);
        });

        glfwSetKeyCallback(windowHandle, keyCallback);

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(windowHandle, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    windowHandle,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowHandle);

        // Enable v-sync
        if (vSync) {
            glfwSwapInterval(1);
        }

        // Make the window visible
        glfwShowWindow(windowHandle);
    }

    private GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                glfwSetWindowShouldClose(window, true);
            }
        }
    };

    public void cleanup() {
        glfwDestroyWindow(windowHandle);
        keyCallback.free();
        glfwTerminate();
        errorCallback.free();
    }
}
