package com.faforerof.rectangles.services.implementation;

import com.faforerof.rectangles.entities.Adjacency;
import com.faforerof.rectangles.entities.Rectangle;
import com.faforerof.rectangles.utilities.RectanglesApplicationUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author faforerof
 */
@ExtendWith(MockitoExtension.class)
@EnableConfigurationProperties
@SpringBootTest(classes = {RectangleOperationLocal.class, RectanglesApplicationUtilities.class})
class RectangleOperationTest {
    @InjectMocks
    private RectangleOperationLocal testObject;
    @Autowired
    private RectanglesApplicationUtilities rectanglesApplicationUtilities;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testObject.setRectanglesApplicationUtilities(rectanglesApplicationUtilities);
    }

    @Test
    void intersectsTrue() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(1.0, 1.0, 10.0, 10.0);
        Assertions.assertTrue(testObject.intersects(r1, r2));

        r1 = new Rectangle(0.0, 0.0, 10.0, 10.0, 45.0);
        r2 = new Rectangle(1.0, 0.0, 10.0, 10.0, 45.0);
        Assertions.assertTrue(testObject.intersects(r1, r2));
    }

    @Test
    void containsTrue() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(1.0, 1.0, 5.0, 5.0);
        Assertions.assertTrue(testObject.contains(r1, r2));
    }

    @Test
    void adjacentTrueWithProper() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(10.0, 0.0, 10.0, 5.0);
        Assertions.assertEquals(Adjacency.PROPER, testObject.adjacent(r1, r2));

        r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        r2 = new Rectangle(-5.0, 0.0, 10.0, 5.0);
        Assertions.assertEquals(Adjacency.PROPER, testObject.adjacent(r1, r2));
    }

    @Test
    void adjacentTrueWithSubLine() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(10.0, 0.0, 9.0, 5.0);
        Assertions.assertEquals(Adjacency.SUB_LINE, testObject.adjacent(r1, r2));

        r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        r2 = new Rectangle(-5.0, 0.0, 9.0, 5.0);
        Assertions.assertEquals(Adjacency.SUB_LINE, testObject.adjacent(r1, r2));
    }

    @Test
    void adjacentTrueWithPartial() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(10.0, 1.0, 100.0, 5.0);
        Assertions.assertEquals(Adjacency.PARTIAL, testObject.adjacent(r1, r2));

        r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        r2 = new Rectangle(10.0, 5.0, -10.0, 5.0);
        Assertions.assertEquals(Adjacency.PARTIAL, testObject.adjacent(r1, r2));
    }


    @Test
    void intersectsFalse() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(20.0, 20.0, 5.0, 100.0);
        Assertions.assertFalse(testObject.intersects(r1, r2));
    }

    @Test
    void containsFalse() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(11.0, 11.0, 5.0, 5.0);
        Assertions.assertFalse(testObject.contains(r1, r2));
    }

    @Test
    void adjacentFalse() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(11.0, 10.0, 5.0, 5.0);
        Assertions.assertEquals(Adjacency.NOT, testObject.adjacent(r1, r2));
    }
}