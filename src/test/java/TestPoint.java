package test.java;
import main.java.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Hadley on 2/23/2019.
 */
public class TestPoint {
    static final double DELTA = .01;
    @Test
    public void TestPoint() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 3);
        Point q1 = new Point(2, 2);
        Point q2 = new Point(0, 2);
        assertTrue(Point.doIntersection(p1, p2, q1, q2));

        p1 = new Point(2, 0);
        p2 = new Point(2, 3);
        q1 = new Point(1, 0);
        q2 = new Point(1, 3);
        assertFalse(Point.doIntersection(p1, p2, q1, q2));

        p1 = new Point(10, 27);
        p2 = new Point(10, 37);
        q1 = new Point(0, 0);
        q2 = new Point(20, 0);
        assertFalse(Point.doIntersection(p1, p2, q1, q2));
    }

    @Test
    public void TestBallBounceLeftSideOfRect() {
        int maxWidth = 100;
        int maxHeight = 100;
        MyBall myBall = new MyBall(5, 10, 10, 10, Constants.BALL_COLOR, maxWidth, maxHeight);
        ArrayList<MyRectangle> entities = new ArrayList<>();
        entities.add(new MyRectangle(12, 0, 20, 20, Constants.RECTANGLE_COLOR));
        assertEquals(myBall.x, 5, DELTA);
        assertEquals(myBall.y, 10, DELTA);
        assertEquals(myBall.getXSpeed(), 10);
        myBall.moveBALL(entities);
        assertEquals(myBall.x, 5, .01);
        assertEquals(myBall.getXSpeed(), -10);
    }
    @Test
    public void TestBallMovesForward() {
        int maxWidth = 100;
        int maxHeight = 100;
        MyBall myBall = new MyBall(5, 10, 10, 10, Constants.BALL_COLOR, maxWidth, maxHeight);
        ArrayList<MyRectangle> entities = new ArrayList<>();
        assertEquals(myBall.x, 5);
        assertEquals(myBall.y, 10);
        assertEquals(myBall.getXSpeed(), 10);
        myBall.moveBALL(entities);
        assertEquals(myBall.x, 5 + 10);
        assertEquals(myBall.getXSpeed(), 10);
    }

    @Test
    public void TestBallBouncesBottomSideOfRect() {
        int maxWidth = 100;
        int maxHeight = 100;
        int xspeed = 0;
        int yspeed = -10;
        MyBall myBall = new MyBallTestable(15, 27, 10, 10, Constants.BALL_COLOR, maxWidth, maxHeight, xspeed, yspeed);
        ArrayList<MyRectangle> entities = new ArrayList<>();
        entities.add(new MyRectangle(0, 0, 20, 20, Constants.RECTANGLE_COLOR));
        assertEquals(myBall.getXSpeed(), 0);
        assertEquals(myBall.getYSpeed(), -10);
        myBall.moveBALL(entities);
        assertEquals(myBall.y, 27);
        assertEquals(10, myBall.getYSpeed());
    }

    @Test
    public void TestBallDoesntBounceBottomSideOfRect() {
        int maxWidth = 100;
        int maxHeight = 100;
        int xspeed = 0;
        int yspeed = -10;
        MyBall myBall = new MyBallTestable(10, 37, 10, 10, Constants.BALL_COLOR, maxWidth, maxHeight, xspeed, yspeed);
        ArrayList<MyRectangle> entities = new ArrayList<>();
        entities.add(new MyRectangle(0, 0, 20, 20, Constants.RECTANGLE_COLOR));
        assertEquals(myBall.getXSpeed(), 0);
        assertEquals(myBall.getYSpeed(), -10);
        myBall.moveBALL(entities);
        assertEquals(27, myBall.y);
        assertEquals(-10, myBall.getYSpeed());
    }

    @Test
    public void TestBallBouncesRightSideOfRect() {
        int maxWidth = 100;
        int maxHeight = 100;
        int xspeed = -10;
        int yspeed = 0;
        MyBall myBall = new MyBallTestable(28, 10, 10, 10, Constants.BALL_COLOR, maxWidth, maxHeight, xspeed, yspeed);
        ArrayList<MyRectangle> entities = new ArrayList<>();
        entities.add(new MyRectangle(0, 0, 20, 20, Constants.RECTANGLE_COLOR));
        myBall.moveBALL(entities);
        assertEquals(28, myBall.x);
        assertEquals(10, myBall.getXSpeed());
    }

    @Test
    public void TestBallBouncesCorrectDirectionRightSideOfRect() {
        int maxWidth = 100;
        int maxHeight = 100;
        int xspeed = -10;
        int yspeed = -10;
        MyBall myBall = new MyBallTestable(28, 20, 10, 10, Constants.BALL_COLOR, maxWidth, maxHeight, xspeed, yspeed);
        ArrayList<MyRectangle> entities = new ArrayList<>();
        entities.add(new MyRectangle(0, 10, 20, 20, Constants.RECTANGLE_COLOR));
        myBall.moveBALL(entities);
        assertEquals(28, myBall.x);
        assertEquals(10, myBall.getXSpeed());
        assertEquals(-10, myBall.getYSpeed());
    }

    @Test
    public void TestBallBouncesCorrectDiagonalCornerBotLeftAndUp() {
        int maxWidth = 100;
        int maxHeight = 100;
        int xspeed = 10;
        int yspeed = -10;
        MyBall myBall = new MyBallTestable(13, 26, 5, 5, Constants.BALL_COLOR, maxWidth, maxHeight, xspeed, yspeed);
        ArrayList<MyRectangle> entities = new ArrayList<>();
        entities.add(new MyRectangle(0, 0, 20, 20, Constants.RECTANGLE_COLOR));
        myBall.moveBALL(entities);
        assertEquals(13, myBall.x);
        assertEquals(10, myBall.getXSpeed());
        assertEquals(10, myBall.getYSpeed());
    }

    @Test
    public void TestBrickIsRemovedWhenHit() {
        int maxWidth = 100;
        int maxHeight = 100;
        int xspeed = -10;
        int yspeed = 0;
        MyBall myBall = new MyBallTestable(23, 10, 5, 5, Constants.BALL_COLOR, maxWidth, maxHeight, xspeed, yspeed);
        ArrayList<MyRectangle> entities = new ArrayList<>();
        entities.add(new MyRectangle(0, 0, 20, 20, Constants.RECTANGLE_COLOR));
        myBall.moveBALL(entities);
        assertEquals(23, myBall.x);
        assertEquals(10, myBall.getXSpeed());
        assertEquals(0, entities.size());
    }

    @Test
    public void TestOnlySingleBrickDestroyedWhenHittingTopOfRectangle() {
        int maxWidth = 1000;
        int maxHeight = 1000;
        int xspeed = 0;
        int yspeed = 10;
        MyBall myBall = new MyBallTestable(400, 100, 10, 10, Constants.BALL_COLOR, maxWidth, maxHeight, xspeed, yspeed);
        ArrayList<MyRectangle> entities = new ArrayList<>();
        entities.add(new MyRectangle(0, 0, 20, 20, Constants.RECTANGLE_COLOR));
        entities.add(new MyRectangle(300, 300, 200, 200, Constants.RECTANGLE_COLOR));
        for (int i = 0; i < 20; i++) {
            myBall.moveBALL(entities);
        }
        assertEquals(1, entities.size());
    }

}
