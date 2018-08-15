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
    private static final String OVER = "GAME OVER press enter to replay";

    private Leaderboard leaderboard;
    private List<Sprite> sprites;
    private static Player player;
    private boolean isGameOver;
    private boolean isStarted = false;
    private int score;
    Thorn th;
    StartPanel sp;

    public static final String EVENT_PLAYER_HIT = "PLAYER IS HIT";
    public static final String EVENT_GAME_START = "STARTING GAME";
    public static final String EVENT_SCORE = "SCORE UPDATED";


    Game() {
        sprites = new ArrayList<Sprite>();
        leaderboard = new Leaderboard();

        //sp = new StartPanel();

        player = new Player();
        th = new Thorn();
        score = 0;

        leaderboard.read("leaderboard.txt");
        init();

        //player.loadImageApp();
    }

    // initialize the stairs
    public void init() {
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
            setChanged();
            notifyObservers(EVENT_PLAYER_HIT);
            System.out.println("Health : " + player.getHealth());
        }
        else{
            player.setInvulnerable(player.getInvulnerable()-1);
        }

        if(isOver()){
            player.setVisible(false);
        }
        score++;
        setChanged();
        notifyObservers(EVENT_SCORE);
    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT)
            player.moveLeft();
        else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT)
            player.moveRight();
        else if (keyCode == KeyEvent.VK_ENTER && isGameOver)
            reset();
        else if(keyCode == KeyEvent.VK_ENTER){
            isStarted = true;
            isGameOver = false;

        }

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
        setChanged();
        notifyObservers(EVENT_GAME_START);
        Color saved = g.getColor();
        g.setColor(new Color( 0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        if(isGameOver){
            centreString(OVER, g, fm, Game.HEIGHT / 2 - 150);
            printLeaderboard(g);
        }
        else{
            centreString(START, g, fm, Game.HEIGHT / 2);
        }

        g.setColor(saved);

    }

    private void printLeaderboard(Graphics g){
        Color saved = g.getColor();
        g.setColor(new Color( 0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(leaderboard.toString());

        int x = Game.WIDTH / 2 - 50;
        int y = Game.HEIGHT / 2 - 150;

        for (String line : leaderboard.toString().split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    // restarts the entire game
    private void reset() {
        System.out.println("resetting game");
        isStarted = true;
        isGameOver = false;
        player = new Player();
        player.setVisible(true);
    }

    // need modification later
    public boolean isOver() {
        if (player.getHealth() < 1) {
            //System.out.println("game over is true");
            isGameOver = true;
            isStarted = false;
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
