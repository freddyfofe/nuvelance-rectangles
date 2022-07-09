package com.faforerof.rectangles.services;

import com.faforerof.rectangles.entities.Adjacency;
import com.faforerof.rectangles.entities.Rectangle;

/**
 * Interface to solve the simple operations between two rectangles
 */
public interface RectangleOperation {

    boolean intersects(Rectangle r1, Rectangle r2);

    boolean contains(Rectangle r1, Rectangle r2);

    Adjacency adjacent(Rectangle r1, Rectangle r2);
}
