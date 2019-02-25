package main.java;

import java.util.ArrayList;

/**
 * Created by Mark Hadley on 2/24/2019.
 */
public class Intersect {
    boolean intersectTop = false;
    boolean intersectBot = false;
    boolean intersectLeft = false;
    boolean intersectRight = false;
    static Intersect doIntersect(MyRectangle rect, Point currentBall, Point oldBall) {
        Intersect intersect = new Intersect();
        if (Point.doIntersection(oldBall,
                currentBall,
                new Point(rect.getX(), rect.getY()),
                new Point(rect.getX() + rect.width, rect.getY()))) {
            // intersection top of rectangle
            intersect.intersectTop = true;
        }
        if (Point.doIntersection(oldBall,
                currentBall,
                new Point(rect.getX(), rect.getY()),
                new Point(rect.getX(), rect.getY() + rect.height))) {
            // intesection left of rectangle
            intersect.intersectLeft = true;
        }
        if (Point.doIntersection(oldBall,
                currentBall,
                new Point(rect.getX() + rect.width, rect.getY()),
                new Point(rect.getX() + rect.width, rect.getY() + rect.height))) {
            // intesection right of rectangle
            intersect.intersectRight = true;
        }
        if (Point.doIntersection(oldBall,
                currentBall,
                new Point(rect.getX(), rect.getY() + rect.height),
                new Point(rect.getX() + rect.width, rect.getY() + rect.height))) {
            // intesection bottom of rectangle
            intersect.intersectBot = true;

        }
        return intersect;
    }
}
