package com.faforerof.rectangles.entities;

import com.faforerof.rectangles.exceptions.RectanglesApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        r = new Rectangle(-5.0,-5.0,10.0,10.0, 240.0);
        topRightPoint = new Point(-1.3397459621556198,-18.66025403784439);
        Assertions.assertEquals(topRightPoint, r.getTopRightPoint());

        r = new Rectangle(0.0,0.0,10.0,10.0);
        topRightPoint = new Point(10.0,10.0);
        Assertions.assertEquals(topRightPoint, r.getTopRightPoint());

        r = new Rectangle(10.0,10.0,5.0,5.0);
        topRightPoint = new Point(15.0,15.0);
        Assertions.assertEquals(topRightPoint, r.getTopRightPoint());
    }

    @Test
    void rectangleCreationWithInvalidParameters () {
        RectanglesApplicationException thrown = Assertions.assertThrows(RectanglesApplicationException.class, () -> {
            new Rectangle(0.0,0.0,0.0,1.0);
        });
        Assertions.assertEquals("Height can not be 0", thrown.getMessage());

        thrown = Assertions.assertThrows(RectanglesApplicationException.class, () -> {
            new Rectangle(0.0,0.0,1.0,0.0);
        });
        Assertions.assertEquals("Width can not be 0", thrown.getMessage());
    }

    @Test
    void getPoints () {
        Rectangle r = new Rectangle(0.0,0.0,10.0,10.0, 45.0);
        List<Point> points = r.getPoints();
        assertAll("points",
                () -> assertEquals(0.0, points.get(0).getX(), 0.00001),
                () -> assertEquals(0.0, points.get(0).getY(), 0.00001),
                () -> assertEquals(7.0710678118654755, points.get(1).getX(), 0.00001),
                () -> assertEquals(7.0710678118654755, points.get(1).getY(), 0.00001),
                () -> assertEquals(0.0, points.get(2).getX(), 0.00001),
                () -> assertEquals(14.142135623730951, points.get(2).getY(), 0.00001),
                () -> assertEquals(-7.0710678118654755, points.get(3).getX(), 0.00001),
                () -> assertEquals(7.0710678118654755, points.get(3).getY(), 0.00001));
        r = new Rectangle(0.0,0.0,10.0,10.0, 135.0);

        List<Point> points2 = r.getPoints();
        assertAll("points",
                () -> assertEquals(0.0, points2.get(0).getX(), 0.00001),
                () -> assertEquals(0.0, points2.get(0).getY(), 0.00001),
                () -> assertEquals(-7.0710678118654755, points2.get(1).getX(), 0.00001),
                () -> assertEquals(7.0710678118654755, points2.get(1).getY(), 0.00001),
                () -> assertEquals(-14.142135623730951, points2.get(2).getX(), 0.00001),
                () -> assertEquals(0.0, points2.get(2).getY(), 0.00001),
                () -> assertEquals(-7.0710678118654755, points2.get(3).getX(), 0.00001),
                () -> assertEquals(-7.0710678118654755, points2.get(3).getY(), 0.00001));
    }
}