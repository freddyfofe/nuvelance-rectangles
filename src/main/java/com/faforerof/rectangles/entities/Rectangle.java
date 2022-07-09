package com.faforerof.rectangles.entities;

import com.faforerof.rectangles.exceptions.RectanglesApplicationException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * Rectangle class, defines one rectangle using the left bottom (x,y) vertex and the width height
 * with this four parameter we know for sure that this object is a rectangle.
 */
@Getter
@Setter
public class Rectangle {
    @NonNull
    private Double x;
    @NonNull
    private Double y;
    @NonNull
    private Double height;
    @NonNull
    private Double width;
    private Double orientation = 0.0;

    public Rectangle(@NonNull Double x, @NonNull Double y, @NonNull Double height, @NonNull Double width) throws RectanglesApplicationException {
        this(x, y, height, width, 0.0);
    }

    public Rectangle(@NonNull Double x, @NonNull Double y, @NonNull Double height, @NonNull Double width, Double orientation) throws RectanglesApplicationException {
        this.x = x;
        this.y = y;
        this.setHeight(height);
        this.setWidth(width);
        this.orientation = orientation;
    }

    public void setHeight(Double height) throws RectanglesApplicationException {
        if (height == 0.0) {
            throw new RectanglesApplicationException("Height can not be 0");
        }
        this.height = height;
    }

    public void setWidth(Double width) throws RectanglesApplicationException {
        if (width == 0.0) {
            throw new RectanglesApplicationException("Width can not be 0");
        }
        this.width = width;
    }

    /**
     * Not implemented yet, set to 0.0
     * @param orientation
     */
    private void setOrientation(Double orientation) {
        this.orientation = orientation;
    }

    public Point getBottomLeftPoint() {
        return new Point(x, y);
    }

    public Point getTopRightPoint() {
        var rad = Math.toRadians(orientation%360);

        double trX = x + width*Math.cos(rad) - width*Math.sin(rad);
        double trY = y + height*Math.sin(rad) + height*Math.cos(rad);

        return new Point(trX, trY);
    }

    public List<Line> getLines() {
        Line l1 = new Line(new Point(x,y), new Point(x + width, y));
        Line l2 = new Line(new Point(x+width,y), new Point(x + width, y + height));
        Line l3 = new Line(new Point(x + width,y + height), new Point(x, y + height));
        Line l4 = new Line(new Point(x,y + height), new Point(x, y));
        return Arrays.asList(l1, l2, l3, l4);
    }
}
