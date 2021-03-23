package com.Wolfaton.game;

public class Main {

    public static void main(String[] args) {
        try {
            boolean vSync = true;
            GameContainerInterface gameContainer = new Wolfaton();
            GameEngine gameEng = new GameEngine("Wolfaton",
                    600, 480, vSync, gameContainer);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }

}
