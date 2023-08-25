package com.objects;

import com.GamePanel;

import java.awt.*;
public class Apple extends Collectable {
    public Apple() {
        super();
        color = Color.red;
    }
    @Override
    public void effect() {
        position = GamePanel.getPointRandomly();
        GamePanel.removePointList(position);
    }

}
