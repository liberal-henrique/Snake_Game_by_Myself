package com.objects;

import java.awt.*;
public class Apple extends Collectable {
    public Apple() {
        super();
        color = Color.black;
    }
    @Override
    public void effect() {
        position = getPointRandomly();
    }

}
