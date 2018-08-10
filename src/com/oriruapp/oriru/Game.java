package com.oriruapp.oriru;

// Represents the game


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Random RANDOM = new Random();


    private List<Sprite> sprites;
    private static Player player;
    private boolean isGameOver;


    Game(){
        sprites = new ArrayList<Sprite>();


    }

    // initialize the stairs
    private void init(){

    }


    // updates the game
    public void update(){

    }

    public void keyPressed(int keyCode){
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT)
            player.faceLeft();
        else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT)
            player.faceRight();
        else if (keyCode == KeyEvent.VK_R && isGameOver)
            reset();
        else if (keyCode == KeyEvent.VK_X)
            System.exit(0);
    }

    public void draw(Graphics g){

    }

    // restarts the entire game
    private void reset(){

    }


}
