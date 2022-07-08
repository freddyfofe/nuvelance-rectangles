package com.faforerof.rectangles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
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

    /**
     * Not implemented yet
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
