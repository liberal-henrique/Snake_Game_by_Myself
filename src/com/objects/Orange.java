package com.objects;
import com.GamePanel;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;

public class Orange extends Collectable {
    private final int delay = 300;
    public Orange() {
        color = Color.orange;
    }
    @Override
    public void effect() {
        position = GamePanel.getPointRandomly();
        GamePanel.removePointList(position);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                GamePanel.clock.setDelay(GamePanel.clock.getDelay() - delay);
            }
        };
        TimerTask t2 = new TimerTask() {
            @Override
            public void run() {}
        };
        timer.schedule(task, 3000);
        timer.schedule(t2, 1000);
        GamePanel.clock.setDelay(GamePanel.clock.getDelay() + delay);
    }
}
