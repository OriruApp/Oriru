package com.oriruapp.oriru;

// Represents the game


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;

public class Game extends Observable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public static final int SPRITE_NUM = 10;
    public static final Random RANDOM = new Random();
    private static final String START = "press enter to START GAME";


    private List<Sprite> sprites;
    private static Player player;
    private boolean isGameOver;
    private boolean isStarted = false;
    Thorn th;
    StartPanel sp;


    Game() {
        sprites = new ArrayList<Sprite>();

        //sp = new StartPanel();

        player = new Player();
        th = new Thorn();

        player.setVisible(false);
        init();
        //player.loadImageApp();
    }

    // initialize the stairs
    public void init() {
        player.setVisible(true);
    }


    // updates the game
    public void update() {
        boolean isOnPlatform = false;
        //sp= new StartPanel();
        Iterator<Sprite> sprite = sprites.iterator();
        while (sprite.hasNext()) {
            Sprite s = sprite.next();

            s.raising();

            if (s.isOnScreenTop()) {
                sprite.remove();
            }
            if (player.isOnPlatform(s)) {
                isOnPlatform = true;
            }
        }
        while (sprites.size() <= SPRITE_NUM) {
            boolean isCollision = false;
            Sprite tmp = new Sprite((int) (Math.random() * WIDTH - 50), (int) (HEIGHT - Math.random() * 200));
            for(Sprite s : sprites){
                if(tmp.isCollision(s)){
                    isCollision = true;
                    break;
                }
            }
            if(!isCollision){
                sprites.add(tmp);
            }
        }
        if (isOnPlatform && !player.isInvulnerable()) {
            player.raising();
        } else {
            player.falling();
        }

        if(player.isHit()){
            player.loseHealth();
            player.setInvulnerable(40);
            System.out.println("Health : " + player.getHealth());
        }
        else{
            player.setInvulnerable(player.getInvulnerable()-1);
        }

        if(isOver()){
            player.setVisible(false);
        }
    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT)
            player.moveLeft();
        else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT)
            player.moveRight();
        else if (keyCode == KeyEvent.VK_R && isGameOver)
            reset();
        else if(keyCode == KeyEvent.VK_ENTER)
            isStarted = true;
        else if (keyCode == KeyEvent.VK_X)
            System.exit(0);
    }

    public void draw(Graphics g) {

        //player.paint(g);
        for (Sprite sprite : sprites) {
            sprite.paint(g);
        }

        if(isStarted){
            player.paint(g);
        }
        else{
            gameStart(g);
        }

        th.paint(g);
    }
    private void gameStart(Graphics g){
        Color saved = g.getColor();
        g.setColor(new Color( 0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(START, g, fm, Game.HEIGHT / 2);
        g.setColor(saved);

    }

    // restarts the entire game
    private void reset() {
        System.out.println("resetting game");
        isGameOver = false;
    }

    // need modification later
    public boolean isOver() {
        if (player.getHealth() < 1) {
            //System.out.println("game over is true");
            isGameOver = true;
        }
        return isGameOver;
    }

    private void checkCollision() {
//        for(Sprite s : sprites){
//            player.falling(s);
//        }

    }

    // Centres a string on the screen
    // modifies: g
    // effects:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int yPos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (Game.WIDTH - width) / 2, yPos);
    }




}
