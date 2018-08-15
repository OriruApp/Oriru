package com.oriruapp.oriru;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ScorePanel extends JPanel implements Observer {

    private JLabel score;
    private JLabel health;
    private int xpos = 600;
    private int ypos = 80;
    private static final String SCORE_TEXT = "SCORE: ";
    private static final String HEALTH_TEXT = "HEALTH: ";
    private int hpLeft = 3;
    private int points = 0;

    public ScorePanel(){
        super();
        setBackground(new Color(180, 180, 180));
        score = new JLabel(SCORE_TEXT + points);
        health = new JLabel(HEALTH_TEXT + hpLeft);
        score.setPreferredSize(new Dimension(100,100));
        health.setPreferredSize(new Dimension(100,100));
        add(score);
        add(Box.createHorizontalStrut(50));
        add(health);
    }

    @Override
    public void update(Observable subject, Object event){
        // Score panel will be updated for any kind of event
        switch ((String) event){
            case Game.EVENT_GAME_START:
                hpLeft = 3;
                points = 0;
                break;
            case Game.EVENT_PLAYER_HIT:
                hpLeft--;
                break;
            case Game.EVENT_SCORE:
                points++;
                break;


        }
        health.setText(HEALTH_TEXT + hpLeft);
        score.setText(SCORE_TEXT + points);

        repaint();
    }
}
