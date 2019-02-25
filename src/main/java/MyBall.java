package main.java;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created by Mark Hadley on 2/23/2019.
 */
public class MyBall extends Ellipse2D.Double {
    protected int xspeed = 10;
    protected int yspeed = 5;
    Color color;
    private final int maxWidth;
    private final int maxHeight;
    private Point oldBall;

    public MyBall(double x, double y, double w, double h, Color c, int maxWidth, int maxHeight) {
        super(x, y, w, h);
        color = c;
        this.width = w;
        this.height = h;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public int getXSpeed() {
        return xspeed;
    }

    public int getYSpeed() {
        return yspeed;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean handleIntersection(Intersect intersect ) {
        boolean intersectTop = intersect.intersectTop;
        boolean intersectBot = intersect.intersectBot;
        boolean intersectLeft = intersect.intersectLeft;
        boolean intersectRight = intersect.intersectRight;
        double oldY = oldBall.y;
        double oldX = oldBall.x;

        if (intersectTop || intersectBot || intersectLeft || intersectRight) {
            if (intersectTop && intersectRight && oldY < y ) {
                yspeed = -yspeed;
            } else if (intersectTop && intersectRight && oldY > y ) {
                xspeed = -xspeed;
            } else if (intersectTop && intersectLeft && oldY > y ) {
                yspeed = -yspeed;
            } else if (intersectTop && intersectLeft && oldY < y ) {
                xspeed = -xspeed;
            } else if (intersectTop) {
                yspeed = -yspeed;
            } else if (intersectBot && intersectRight ) {
                if (oldY > y) {
                    yspeed = -yspeed;
                } else {
                    xspeed = -xspeed;
                }
            } else if (intersectBot && intersectLeft ) {
                if (oldY > y) {
                    yspeed = -yspeed;
                } else {
                    xspeed = -xspeed;
                }
            } else if (intersectBot) {
                yspeed = -yspeed;
            } else if (intersectLeft || intersectRight) {
                xspeed = -xspeed;
            }
            x = oldX;
            y = oldY;
            return true;
        }
        return false;
    }

    public int moveBALL(ArrayList<MyRectangle> entities) {
        // check collision with entities
        double radius = height / 2;
        double oldX = x;
        double oldY = y;
        x = x + xspeed;
        y = y + yspeed;
        oldBall = new Point(oldX, oldY);
        Point currentBall = new Point(x, y);
        for (MyRectangle e : entities) {
            Intersect intersect = Intersect.doIntersect(e, currentBall, oldBall);
            e.handleBallBounce(handleIntersection(intersect));
        }
        entities.removeIf(e -> e.markedForRemoval);

        // clean up all hit bricks (should only ever be one)

        // check play area boundaries
        if (x < 0) {
            x = 0;
            xspeed = -xspeed;
        } else if (x > maxWidth - ((int) getWidth() / 2)) {
            x = maxWidth - ((int) getWidth() / 2);
            xspeed = -xspeed;
        }
        if (y < 0) {
            y = 0;
            yspeed = -yspeed;
        } else if (y > maxHeight - ((int) getHeight() / 2)) {
            return 1;
//            y = maxHeight - ((int) getHeight() / 2);
//            yspeed = -yspeed;
        }
        return 0;
    }
}
