package com.objects;
import com.GamePanel;
import java.awt.*;
import java.util.*;
abstract class Collectable extends BaseObject {
    Collectable() {
        position = GamePanel.getPointRandomly();
        GamePanel.removePointList(position);
    }
    public abstract void effect();

    @Override
    public void onCollision(BaseObject obj) {
        effect();
    }
}
