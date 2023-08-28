package com;
import com.objects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements ElementBasic, ActionListener {
    ArrayList<BaseObject> objectList = new ArrayList<>();
    private static final LinkedList<Point> allPointsList = new LinkedList<>();
    public static final LinkedList<Point> cornerCases = new LinkedList<>();
    public final Snake snake;
    public static boolean running = false;
    public static Timer clock;
    public static int DELAY = 150;
    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_W, SCREEN_H));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter( ) {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
            }
        });
        clock = new javax.swing.Timer(DELAY, this);
        fillPointList();
        snake = new Snake(4 * UNIT_SIZE, UNIT_SIZE);
        running = true;
        objectList.add(snake);
        objectList.add(new Apple());
        objectList.add(new Orange());
        objectList.add(new Bomb());
        clock.start();
    }
    public static void fillPointList() {
        for (int x = 0; x <= SCREEN_W/UNIT_SIZE; x++) {
            for (int y = 0; y <= SCREEN_H / UNIT_SIZE; y++) {
                allPointsList.add(new Point(x * UNIT_SIZE, y * UNIT_SIZE));
                if (x == 0 || y == 0 || y == SCREEN_H/UNIT_SIZE - 1 || x == SCREEN_W/UNIT_SIZE - 1) {
                    Point p = new Point(x*UNIT_SIZE, y*UNIT_SIZE);
                    cornerCases.add(new Point(x * UNIT_SIZE, y * UNIT_SIZE));
                    removePointList(p);
                }
            }
        }
    }
    public static void addPointlist(Point p) {
        allPointsList.add(p);
    }
    public static void removePointList(Point p) {
       allPointsList.remove(p);
    }
    public static Point getPointRandomly() {
        Random random = new Random();
        int randomIndex = random.nextInt(GamePanel.allPointsList.size());
        return GamePanel.allPointsList.get(randomIndex);
    }
    public static Point getWallPointsRandomly() {
        Random random = new Random();
        Point p = new Point();
        int randomIndex = random.nextInt(GamePanel.cornerCases.size());
        p = GamePanel.cornerCases.get(randomIndex);
        GamePanel.cornerCases.remove(p);
        return (p);
    }
    protected void paintComponent(Graphics g) {
        if (running) {
            super.paintComponent(g);
            draw(g);
            objectList.forEach(e -> e.draw(g));
//            cornerCases.forEach(e -> {
//                g.setColor(Color.BLUE);
//                g.fillRect(12 + e.x, 12 + e.y, 10, 10);
//            });
            allPointsList.forEach(e -> {
                g.setColor(Color.black);
                g.fillRect(10 + e.x, 10 + e.y, 5, 5);
            });
            //drawWall(g);
        }
        else
            game_over(g);
    }
    public void game_over(Graphics g) {
        g.setColor(Color.red);
        this.setBackground(Color.black);
        g.setFont(new Font("Ink Free", Font.BOLD, 55));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_W- metrics.stringWidth("Game Over"))/2, SCREEN_H/2);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < SCREEN_H/UNIT_SIZE; i++) {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_H);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_W, i*UNIT_SIZE);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        detectCollision();
        if (cornerCases.size() > 0) {
            startFillingWalls( );
        }
        repaint();
    }
    private void detectCollision() {
        objectList.forEach(e -> {
            if (e != snake && e.position.distance(snake.position) == 0) {
                snake.onCollision(e);
                e.onCollision(snake);
            }
        });
        snake.corpCollision();
    }

    public void startFillingWalls() {
        int time_to_wait = 5000;
        ActionListener fillingAction = new ActionListener( ) {
            @Override
            public void actionPerformed (ActionEvent e) {
                objectList.add(new Wall());
            }
        };
        Timer clock = new Timer(time_to_wait, fillingAction);
        clock.setInitialDelay(time_to_wait);
        clock.start();
    }
}
