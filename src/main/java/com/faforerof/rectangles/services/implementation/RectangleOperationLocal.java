package com.faforerof.rectangles.services.implementation;

import com.faforerof.rectangles.entities.Rectangle;
import com.faforerof.rectangles.services.RectangleOperation;
import org.springframework.stereotype.Service;

@Service
public class RectangleOperationLocal implements RectangleOperation {


    @Override
    public boolean intersects(Rectangle r1, Rectangle r2) {
        return false;
    }

    @Override
    public boolean contains(Rectangle r1, Rectangle r2) {
        return false;
    }

    @Override
    public boolean adjacent(Rectangle r1, Rectangle r2) {
        return false;
    }
}
