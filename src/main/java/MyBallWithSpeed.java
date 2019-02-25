package main.java;

import java.awt.*;

public class MyBallWithSpeed extends MyBall {
    public MyBallWithSpeed(double x, double y, double w, double h, Color c, int maxWidth, int maxHeight, int xspeed, int yspeed) {
        super(x, y, w, h, c, maxWidth, maxHeight);
        this.xspeed = xspeed;
        this.yspeed = yspeed;
    }
}
