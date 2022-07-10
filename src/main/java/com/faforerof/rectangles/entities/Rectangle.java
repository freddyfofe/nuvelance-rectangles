package com.faforerof.rectangles.entities;

import com.faforerof.rectangles.exceptions.RectanglesApplicationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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

    public Point getBottomLeftPoint() {
        return new Point(x, y);
    }

    public Point getTopRightPoint() {
        var rad = Math.toRadians(orientation % 360);
        double trX = x + width * Math.cos(rad) - width * Math.sin(rad);
        double trY = y + height * Math.sin(rad) + height * Math.cos(rad);

        return new Point(trX, trY);
    }

    public List<Point> getPoints() {
        var rad = Math.toRadians(orientation % 360);

        double x1 = x + width * Math.cos(rad);
        double y1 = y + height * Math.sin(rad);

        double x2 = x + width * Math.cos(rad) - width * Math.sin(rad);
        double y2 = y + height * Math.sin(rad) + height * Math.cos(rad);

        double x3 = x - height * Math.sin(rad);
        double y3 = y + height * Math.cos(rad);

        Point p = new Point(x, y);
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Point p3 = new Point(x3, y3);

        return Arrays.asList(p, p1, p2, p3);
    }

    public List<Line> getLines() {
        List<Point> p = this.getPoints();
        Line l1 = new Line(p.get(0), p.get(1));
        Line l2 = new Line(p.get(1), p.get(2));
        Line l3 = new Line(p.get(2), p.get(3));
        Line l4 = new Line(p.get(3), p.get(0));
        return Arrays.asList(l1, l2, l3, l4);
    }
}
