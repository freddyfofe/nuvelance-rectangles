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
class RectangleOperationEdgeCasesTest {
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
    void intersectsFalse() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Assertions.assertFalse(testObject.intersects(r1, r2));

        r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        r2 = new Rectangle(0.0, 0.0, 8.0, 10.0);
        Assertions.assertFalse(testObject.intersects(r1, r2));
    }

    @Test
    void intersectsTrue() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(0.0, 1.0, 5.0, 5.0, 45.0);
        Assertions.assertTrue(testObject.intersects(r1, r2));
    }

    @Test
    void containsFalse() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(0.0, 1.0, 5.0, 5.0, 45.0);
        Assertions.assertFalse(testObject.contains(r2, r1));

        r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        r2 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Assertions.assertFalse(testObject.contains(r1, r2));
    }

    @Test
    void adjacentFalse() {
        Rectangle r1 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(0.0, 1.0, 5.0, 5.0, 45.0);
        Assertions.assertEquals(Adjacency.NOT, testObject.adjacent(r1, r2));
    }
}