package com.objects;

import java.awt.*;
import java.util.*;
abstract class Collectable extends BaseObject {
    Collectable() {
        position = getPointRandomly();
    }
    public abstract void effect();

     static Point getPointRandomly() {
        Random random = new Random();
        int randomX = (random.nextInt((int)(SCREEN_W/UNIT_SIZE))*UNIT_SIZE);
        int randomY = ((random.nextInt((int)SCREEN_H/UNIT_SIZE))*UNIT_SIZE);
        return (new Point(randomX, randomY));
    }

    @Override
    public void onCollision(BaseObject obj) {
        effect();
    }
}
