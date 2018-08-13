package com.oriruapp.oriru;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public class ScorePanel extends JPanel {

    private JLabel score;
    private JLabel health;
    private int xpos = 600;
    private int ypos = 80;
    private static final String SCORE_TEXT = "SCORE:";
    private static final String HEALTH_TEXT = "HEALTH: ";

    public ScorePanel(Game g){
        super();
        setBackground(new Color(180, 180, 180));
        score = new JLabel(SCORE_TEXT);
        health = new JLabel(HEALTH_TEXT);
        score.setPreferredSize(new Dimension(100,100));
        health.setPreferredSize(new Dimension(100,100));
        add(score);
        add(Box.createHorizontalStrut(50));
        add(health);
    }
}
