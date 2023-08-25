package com.objects;

import com.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class Snake extends BaseObject {
    int BodyParts = 7;
    Color snakeColor = Color.pink;
    directions direction =  directions.Right;
    public ArrayList<Point> datalist = new ArrayList<>();
    private Point lastPosition = null;
    public Snake(int x, int y) {
        position.setLocation( x, y );
        for (int i = 0; i < BodyParts; i++) {
            Point p = new Point(x - (i * UNIT_SIZE), y);
            datalist.add(p);
            GamePanel.removePointList(p);
        }
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(snakeColor);
        for (Point p : datalist)
            g.fillRect(p.x, p.y, UNIT_SIZE, UNIT_SIZE);
    }
    public void move() {
        Point p = new Point(position);
        switch (direction) {
            case Right:
                p.x = (p.x + UNIT_SIZE) >= SCREEN_W ? 0 : p.x + UNIT_SIZE;
                break ;
            case Left:
                p.x = ((p.x - UNIT_SIZE) < 0 ? SCREEN_W : p.x) - UNIT_SIZE;
                break;
            case Up:
                p.y = ((p.y - UNIT_SIZE) < 0 ? SCREEN_H : p.y) - UNIT_SIZE;
                break;
            case Down:
                p.y = (p.y + UNIT_SIZE) >= SCREEN_H ? 0 : p.y + UNIT_SIZE;
                break;
        }
        lastPosition = datalist.remove(datalist.size() - 1);
        GamePanel.addPointlist(lastPosition);
        datalist.add(0, p);
        GamePanel.removePointList(p);
        position = p;
    }
    public void keyPressed(KeyEvent e) {
        switch ( e.getKeyCode( )) {
            case KeyEvent.VK_RIGHT:
                if (direction != directions.Left)
                    direction = directions.Right;
                break;
            case KeyEvent.VK_LEFT:
                if (direction != directions.Right)
                    direction = directions.Left;
                break;
            case KeyEvent.VK_UP:
                if (direction != directions.Down)
                    direction = directions.Up;
                break;
            case KeyEvent.VK_DOWN:
                if (direction != directions.Up)
                    direction = directions.Down;
                break;
            case KeyEvent.VK_ESCAPE:
                System.out.println("Get out");
                break;
        }
    }
    @Override
    public void onCollision(BaseObject obj) {
        if (obj instanceof  Apple) {
            datalist.add(lastPosition);
            GamePanel.removePointList(lastPosition);
        }
    }
    public void corpCollision() {
        Point headPoint = datalist.get(0);
        for (int i = 1; i < datalist.size(); i++) {
            Point currentPoint = datalist.get(i);
            if (currentPoint.x == position.x && currentPoint.y == position.y) {
                GamePanel.running = false;
                break ;
            }
        }
    }
}
