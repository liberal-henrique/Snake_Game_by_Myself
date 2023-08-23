package com.objects;

import java.awt.*;

public class BaseObject implements ElementBasic, Collision {
    public Point position = new Point( );
    Color color = Color.red;
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x, position.y, UNIT_SIZE, UNIT_SIZE);
    }
    @Override
    public void onCollision(BaseObject obj) {
    }
}
