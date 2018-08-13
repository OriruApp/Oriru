package com.oriruapp.oriru;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    private JButton startButton;

    public StartPanel() {
        super();
        setPreferredSize(new Dimension(600, 800));
        setBackground(Color.white);
        startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(startButton);

    }

    public JButton getButton(){
        return startButton;
    }
}
