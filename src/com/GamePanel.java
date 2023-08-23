package com;

import com.objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ElementBasic, ActionListener {
    Snake snake = new Snake(5 * UNIT_SIZE, UNIT_SIZE);
    public static Timer timer;
    ArrayList<BaseObject> objectList = new ArrayList<>();
    public static int DELAY = 150;
    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_W, SCREEN_H));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter( ) {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
            }
        });
        timer = new Timer(DELAY, this);
        objectList.add(snake);
        objectList.add(new Apple());
        objectList.add(new Orange());
        timer.start();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        //Just to learn about the com.objects.Snake's collision
        objectList.forEach(e -> e.draw(g));
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < SCREEN_H/UNIT_SIZE; i++) {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_H);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_W, i*UNIT_SIZE);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        detectCollision();
        repaint();
    }
    private void detectCollision() {
        objectList.forEach(e -> {
            if (e != snake && e.position.distance(snake.position) == 0)
            {
                snake.onCollision(e);
                e.onCollision(snake);
            }
        });
    }
}
