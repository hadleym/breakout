package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Mark Hadley on 2/24/2019.
 */
class BreakoutPanel extends JPanel implements KeyListener {

    int width = Constants.WINDOW_WIDTH, height = Constants.WINDOW_HEIGHT;
    ArrayList<MyRectangle> entities;
    private int xspeed;
    private int yspeed;
    MyBall ball;
    Paddle paddle;
    Point topleft = new Point(Constants.GAP, 100);
    Point botRight = new Point(Constants.WINDOW_WIDTH - Constants.GAP,100);


    private void loseLife() {
        ball = new MyBallWithSpeed(400, 100, 10, 10, Color.RED, width, height, xspeed, yspeed );

    }
    private void init() {
        entities = new ArrayList<>();
        paddle = new Paddle();
        xspeed = 10;
        yspeed = 10;
        ball = new MyBallWithSpeed(400, 100, 10, 10, Color.RED, width, height, xspeed, yspeed );
        entities = new ArrayList<>();
        int brickWidth = 30;
        int brickHeight = 10;
        int brickGap = 40;
        int rowGap = 30;
        int rowCount = 3;
        int colCount = 15;
        addEntity(paddle);
//        addEntity(300, 300, 200, 200, Constants.RECTANGLE_COLOR);
//        addEntity(0, 0, 20, 20, Constants.RECTANGLE_COLOR);
        for (int r = 0; r < rowCount; r++ ) {
            for (int c = 0; c < colCount; c++) {
                addEntity(c * (brickWidth + brickGap), 100 + (brickHeight+rowGap) * r, brickWidth, brickHeight, Constants.RECTANGLE_COLOR);
            }
        }
    }
    BreakoutPanel() {
        init();
    }

    void addEntity(MyRectangle rect) {
        entities.add(rect);
    }
    void addEntity(int x, int y, int w, int h, Color c) {
        entities.add(new MyRectangle(x, y, w, h, c));
    }

    void moveEntities() {
        paddle.movePaddle();
        if (ball.moveBALL(entities) == 1) {
            loseLife();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(ball.getColor());
        g2d.fillOval((int) ball.x, (int) ball.y, (int) ball.width, (int) ball.height);
        g2d.setColor(paddle.getColor());
        g2d.fillRect((int) paddle.x, (int) paddle.y, (int) paddle.width, (int) paddle.height);
        for (MyRectangle entity: entities) {
            g2d.setColor(entity.getColor());
            g2d.fillRect((int) entity.x, (int) entity.y, (int) entity.width, (int) entity.height);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                paddle.moveLeft();
                break;
            case KeyEvent.VK_D:
                paddle.moveRight();
                break;
            case KeyEvent.VK_Q:
                System.out.println("Quitting...");
                init();
//                this.processEvent( new EndGameEvent(this, 1, "EndGameEvent"));
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                paddle.stopMovingLeft();
                break;
            case KeyEvent.VK_D:
                paddle.stopMovingRight();
                break;
        }
    }

}