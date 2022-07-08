package com.faforerof.rectangles.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void getBottomLeftPoint() {
        Rectangle r = new Rectangle(0.0,0.0,10.0,10.0, 90.0);
        Point bottomLeftPoint = new Point(0.0,0.0);
        Assertions.assertEquals(bottomLeftPoint, r.getBottomLeftPoint());

        r = new Rectangle(10.0,10.0,10.0,10.0, 90.0);
        bottomLeftPoint = new Point(10.0,10.0);
        Assertions.assertEquals(bottomLeftPoint, r.getBottomLeftPoint());
    }

    @Test
    void getTopRightPoint() {
        Rectangle r = new Rectangle(0.0,0.0,10.0,10.0, 90.0);
        Point topRightPoint = new Point(-10.0,10.0);
        Assertions.assertEquals(topRightPoint, r.getTopRightPoint());

        r = new Rectangle(0.0,0.0,10.0,10.0, 60.0);
        topRightPoint = new Point(-3.6602540378443846,13.660254037844386);
        Assertions.assertEquals(topRightPoint, r.getTopRightPoint());

        r = new Rectangle(10.0,10.0,10.0,10.0, 60.0);
        topRightPoint = new Point(6.3397459621556145,23.660254037844386);
        Assertions.assertEquals(topRightPoint, r.getTopRightPoint());

        r = new Rectangle(0.0,0.0,10.0,10.0);
        topRightPoint = new Point(10.0,10.0);
        Assertions.assertEquals(topRightPoint, r.getTopRightPoint());

        r = new Rectangle(10.0,10.0,5.0,5.0);
        topRightPoint = new Point(15.0,15.0);
        Assertions.assertEquals(topRightPoint, r.getTopRightPoint());
    }
}