package com.faforerof.rectangles.services.implementation;

import com.faforerof.rectangles.entities.Rectangle;
import com.faforerof.rectangles.services.RectangleOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author faforerof
 */
@ExtendWith(MockitoExtension.class)
class RectangleOperationTest {
    @InjectMocks
    private RectangleOperation testObject = new RectangleOperationLocal();


    @Test
    void intersectsTrue() {
        Rectangle r1 = new Rectangle(0.0,0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(1.0,0.0, 5.0, 100.0);
        Assertions.assertTrue(testObject.intersects(r1, r2));
    }

    @Test
    void containsTrue() {
        Rectangle r1 = new Rectangle(0.0,0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(1.0,1.0, 5.0, 5.0);
        Assertions.assertTrue(testObject.contains(r1, r2));
    }

    @Test
    void adjacentTrue() {
        Rectangle r1 = new Rectangle(0.0,0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(1.0,0.0, 5.0, 5.0);
        Assertions.assertTrue(testObject.contains(r1, r2));
    }


    @Test
    void intersectsFalse() {
        Rectangle r1 = new Rectangle(0.0,0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(20.0,20.0, 5.0, 100.0);
        Assertions.assertFalse(testObject.intersects(r1, r2));
    }

    @Test
    void containsFalse() {
        Rectangle r1 = new Rectangle(0.0,0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(11.0,11.0, 5.0, 5.0);
        Assertions.assertFalse(testObject.contains(r1, r2));
    }

    @Test
    void adjacentFalse() {
        Rectangle r1 = new Rectangle(0.0,0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(11.0,10.0, 5.0, 5.0);
        Assertions.assertFalse(testObject.contains(r1, r2));
    }
}