package main.java;

import java.awt.*;

/**
 * Created by Mark Hadley on 2/24/2019.
 */
public class Paddle extends MyRectangle {
    static final int PADDLE_WIDTH = 100;
    static final int PADDLE_HEIGHT = 10;
    static final int PADDLE_STARTING_X = 100;
    static final int PADDLE_STARTING_Y = 500;
    static final Color PADDLE_COLOR = Color.BLUE;
    int xspeed = 0;
    int yspeed = 0;

    public Paddle(){
        super(PADDLE_STARTING_X, PADDLE_STARTING_Y,  PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
    }

    public Color getColor() {
        return PADDLE_COLOR;
    }

    public void moveLeft() {
        this.xspeed = -10;
    }
    public void moveRight() {
        this.xspeed = 10;
    }

    public void movePaddle() {
        x += xspeed;
    }

    public void stopMovingLeft() {
        if (xspeed < 0) {
            xspeed = 0;
        }
    }

    public void stopMovingRight() {
        if (xspeed > 0) {
            xspeed = 0;
        }
    }

    @Override
    public void handleBallBounce(boolean b) {
        // do nothing
    }
}
