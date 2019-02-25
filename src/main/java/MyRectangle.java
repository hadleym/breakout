package main.java;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Mark Hadley on 2/23/2019.
 */
public class MyRectangle extends Rectangle2D.Double {
    Color color;
    public boolean markedForRemoval = false;
    public MyRectangle(double x, double y, double w, double h, Color c) {
        super(x, y, w, h);

        color = c;
    }

    public void handleBallBounce(boolean b) {
        markedForRemoval = b;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
