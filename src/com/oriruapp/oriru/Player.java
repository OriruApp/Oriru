package com.oriruapp.oriru;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Component{

    private int health;
    private BufferedImage img;
    private int xpos;
    private int ypos;
    private static int VELOCITY = 25;


    private static final Color COLOR = new Color(250, 128, 20);

    public Player(){
        loadImageApp();
        repaint();
        xpos = 100;
        ypos = 100;
        //paint(this);
    }

    public int getHealth(){
        return health;
    }
    public void loadImageApp() {
        System.out.println("in loadImage");
        try {
            img = ImageIO.read(new File("assets/man.png"));
        } catch (IOException e) {

        }

    }

    @Override
    public void paint(Graphics g){
        System.out.println("in paint");
        super.paint(g);
        g.drawImage(img,xpos,ypos,this);
    }
    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100,100);
        } else {
            return new Dimension(img.getWidth(null), img.getHeight(null));
        }
    }

    public void moveLeft(){
        xpos-= VELOCITY;
    }

    public void moveRight(){
        xpos+= VELOCITY;
    }
}
