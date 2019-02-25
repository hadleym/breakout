package test.java;

import main.java.demos.breakout.MyBall;

import java.awt.*;

/**
 * Created by Mark Hadley on 2/23/2019.
 */
public class MyBallTestable extends MyBall {
    public MyBallTestable(int x, int y, int h, int w, Color c, int maxH, int maxW, int xSpeed, int ySpeed) {
        super(x, y, h, w, c, maxH, maxW);
        this.xspeed = xSpeed;
        this.yspeed = ySpeed;
    }
}
