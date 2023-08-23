package com.objects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake extends BaseObject {
    int BodyParts = 4;
    Color snakeColor = Color.pink;
    directions direction =  directions.Right;
    ArrayList<Point> datalist = new ArrayList<>();

    private Point lastPosition = null;
    public Snake(int x, int y) {
        position.setLocation( x, y );
        for (int i = 0; i < BodyParts; i++)
            datalist.add(new Point(x - (i * UNIT_SIZE), y));
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(snakeColor);
        for (Point p : datalist)
            g.fillRect(p.x, p.y, UNIT_SIZE, UNIT_SIZE);
        g.setColor(Color.red);
        g.fillRect(position.x, position.y, UNIT_SIZE, UNIT_SIZE);
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
        datalist.add(0, p);
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
        }
    }
    @Override
    public void onCollision(BaseObject obj) {
        if (obj instanceof  Apple)
           datalist.add(lastPosition);
    }
}
