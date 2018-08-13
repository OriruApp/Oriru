package com.oriruapp.oriru;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Thorn extends Component{
    private BufferedImage img;
    private int ypos = 0;
    private int xpos = 0;
    public Thorn(){
        System.out.println("building thorn");
        loadImageApp();
        repaint();
    }

    public void loadImageApp() {
        try {
            img = ImageIO.read(new File("assets/spike.png"));
        } catch (IOException e) {
            System.out.println("in exception");
        }

    }

    @Override
    public void paint(Graphics g){
        //System.out.println("Staircase in paint");
        super.paint(g);
        g.drawImage(img,xpos,ypos,this);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.xpos, this.ypos
                , img.getWidth(null), img.getHeight(null));
    }

}
