package com.oriruapp.oriru;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Component {

    private int health = 3;
    private int invulnerable = 0;
    private BufferedImage img;
    private int xpos;
    private int ypos;
    private static int VELOCITY = 5;
    private static int HORIZONTALVELOCITY = 10;
    private static int SPRITE_VELOCITY = 3;
    private static int PLAYERHEIGHT = 16;


    private static final Color COLOR = new Color(250, 128, 20);

    public Player() {
        loadImageApp();
        repaint();
        xpos = 100;
        ypos = 100;
        //falling();
        //paint(this);
    }

    public int getHealth() {
        return health;
    }

    public void loadImageApp() {
        //System.out.println("in loadImage");
        try {
            img = ImageIO.read(new File("assets/man.png"));
        } catch (IOException e) {

        }

    }

    @Override
    public void paint(Graphics g) {
        //System.out.println("in paint");
        if(isVisible()) {
            super.paint(g);
            g.drawImage(img, xpos, ypos, this);
        }
    }

    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100, 100);
        } else {
            return new Dimension(img.getWidth(null), img.getHeight(null));
        }
    }

    public void moveLeft() {
        xpos -= HORIZONTALVELOCITY;
    }

    public void moveRight() {
        xpos += HORIZONTALVELOCITY;
    }

    public void falling() {
        //System.out.println("in falling");


        // if statement here to help debugging; will be removed later.
        //System.out.println(isOnPlatform(s));
        if (ypos < 800 - PLAYERHEIGHT) {
            ypos += VELOCITY;

            //System.out.println(ypos);
        }
        else {
            this.health = 0;
        }

    }

    public int getYpos() {
        return ypos;
    }

//    public int getHeight(){
//        return PLAYERHEIGHT;
//    }


    public int getInvulnerable() {
        return invulnerable;
    }

    public void setInvulnerable(int time){
        this.invulnerable = time;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.xpos, this.ypos
                , img.getWidth(null), img.getHeight(null));
    }

    //checks if the player is standing on the platform
    public boolean isOnPlatform(Sprite other) {
        Rectangle thisBoundingRect = this.getBounds();
        Rectangle otherBoundingRect = other.getBounds();

        return thisBoundingRect.intersects(otherBoundingRect);
    }

    public boolean isHit() {
        if(this.ypos < 30 && invulnerable <= 0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isInvulnerable(){
        return (invulnerable > 0);
    }

    public void loseHealth() {
        if(isVisible()){
            health--;
        }
        return;

    }

    public void raising() {
        if (this.ypos >= -img.getHeight(null)) {
            this.ypos -= SPRITE_VELOCITY;
        }
    }

}
