package com.objects;
import com.GamePanel;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;

public class Orange extends Collectable {
    private int delay = 50;
    public Orange() {
        color = Color.orange;
    }
    @Override
    public void effect() {
        Timer timer = new Timer();
        position = GamePanel.getPointRandomly();
        GamePanel.removePointList(position);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                GamePanel.timer.setDelay(GamePanel.timer.getDelay() - delay);
            }
        };
        TimerTask t2 = new TimerTask() {
            @Override
            public void run() {}
        };
        timer.schedule(task, 3000);
        timer.schedule(t2, 1000);
        GamePanel.timer.setDelay(GamePanel.timer.getDelay() + delay);
    }
}
