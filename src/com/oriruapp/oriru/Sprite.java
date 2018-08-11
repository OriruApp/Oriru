package com.oriruapp.oriru;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// SPRITE REPRESENTS THE STAIRCASES IN OUR GAME
public class Sprite extends Component{

    private BufferedImage img;
    private int xpos;
    private int ypos;


    // every staircase have the same size
    private static int WIDTH;
    private static int HEIGHT;

    public Sprite(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;

        loadImageApp();
        repaint();
        //paint(this);
    }

    public void draw(){

    }

    public void loadImageApp() {
        System.out.println("Staircase in loadImage");
        try {
            img = ImageIO.read(new File("assets/staircase.png"));
        } catch (IOException e) {

        }

    }

    @Override
    public void paint(Graphics g){
        System.out.println("Staircase in paint");
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




}