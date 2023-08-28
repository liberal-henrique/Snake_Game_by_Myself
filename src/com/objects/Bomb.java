package com.objects;
import com.GamePanel;
import java.awt.*;
public class Bomb extends Collectable {
    public Bomb ( ) {
        color = Color.black;
    }
    public void effect() {
        position = GamePanel.getPointRandomly();
        GamePanel.removePointList(position);
    }
}
