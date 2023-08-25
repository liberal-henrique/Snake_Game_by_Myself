package com.objects;

import java.awt.*;
public interface ElementBasic {
    int SCREEN_W = 600;
    int SCREEN_H = 600;
    int UNIT_SIZE = 25;
    void draw(Graphics g);
    enum directions {
        Down,
        Left,
        Right,
        Up,
    }
}
