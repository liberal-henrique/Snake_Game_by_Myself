package com.objects;

import com.GamePanel;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Wall extends BaseObject implements ElementBasic {
    public Wall() {
        color = Color.GRAY;
        if (!GamePanel.cornerCases.isEmpty()) {
            position = GamePanel.getWallPointsRandomly();
        }
    }
    public void effect() {
    }


}
