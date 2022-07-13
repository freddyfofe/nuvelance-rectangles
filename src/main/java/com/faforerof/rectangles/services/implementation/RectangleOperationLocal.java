package com.faforerof.rectangles.services.implementation;

import com.faforerof.rectangles.entities.Adjacency;
import com.faforerof.rectangles.entities.Line;
import com.faforerof.rectangles.entities.Point;
import com.faforerof.rectangles.entities.Rectangle;
import com.faforerof.rectangles.services.RectangleOperation;
import com.faforerof.rectangles.utilities.RectanglesApplicationUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Simple Implementation of the Rectangles Operations interface
 */
@Service
public class RectangleOperationLocal implements RectangleOperation {

    private RectanglesApplicationUtilities rectanglesApplicationUtilities;

    @Override
    public boolean intersects(Rectangle r1, Rectangle r2) {

        List<Boolean> results = new ArrayList<>();
        for (Point p :
                r2.getPoints()) {
            List<Point> points = r1.getLines().stream().map(
                    line -> getYValueForXInLine(p.getX(), line))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (points.isEmpty()) {
                results.add(false);
            } else if ((points.get(0).getY() >= p.getY() || p.getY() >= points.get(1).getY())
                    && (points.get(1).getY() >= p.getY() || p.getY() >= points.get(0).getY())) {
                results.add(false);
            } else {
                results.add(true);
            }
        }

        return results.stream().filter(aBoolean -> !aBoolean).count()
                != 0 && results.stream().filter(aBoolean -> aBoolean).count() != 0;
    }

    /**
     * Returns the containment of two rectangles
     * @param r1
     * @param r2
     * @return true if Rectangle r2 is inside of Rectangle r1
     */
    @Override
    public boolean contains(Rectangle r1, Rectangle r2) {
        for (Point p :
                r2.getPoints()) {
            List<Point> points = r1.getLines().stream().map(
                    line -> getYValueForXInLine(p.getX(), line))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (points.isEmpty() || points.size() < 2) {
                return false;
            } else if ((points.get(0).getY() >= p.getY() || p.getY() >= points.get(1).getY())
                    && (points.get(1).getY() >= p.getY() || p.getY() >= points.get(0).getY())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Defines if two Rectangles are adjacent or not.
     * @param r1
     * @param r2
     * @return
     */
    @Override
    public Adjacency adjacent(Rectangle r1, Rectangle r2) {
        if (this.contains(r1, r2) || this.contains(r2, r1)) {
            return Adjacency.NOT;
        }
        Optional<Adjacency> result = r1.getLines().stream().map(
                line1 ->
                    r2.getLines().stream().map(line2 ->
                        compareLines(line1, line2))
                            .collect(Collectors.toList())
            ).flatMap(List::stream)
                .filter(adjacency -> !Adjacency.NOT.equals(adjacency))
                    .collect(Collectors.toList()).stream().findFirst();

        if (result.isPresent()) {
            return result.get();
        }
        return Adjacency.NOT;
    }

    /**
     * Compare two Lines and defines their Adjacency
     * @param l1
     * @param l2
     * @return the Adjacency relationship of the two Lines.
     */
    private Adjacency compareLines(Line l1, Line l2) {
        if ( arePointsEqualsWithAccuracy(l1.getStart(), l2.getStart())
                && arePointsEqualsWithAccuracy(l1.getEnd(), l2.getEnd())
        || arePointsEqualsWithAccuracy(l1.getStart(), l2.getEnd())
                && arePointsEqualsWithAccuracy(l1.getEnd(), l2.getStart())) {
            return Adjacency.PROPER;
        } else if(isPointInLineBounds(l2.getStart(), l1) && isPointInLineBounds(l2.getEnd(), l1)) {
            return Adjacency.SUB_LINE;
        } else if (
                ((isPointInLineBoundsExclusive(l2.getStart(), l1) && !isPointInLineBounds(l2.getEnd(), l1))
                || (isPointInLineBoundsExclusive(l2.getEnd(), l1) && !isPointInLineBounds(l2.getStart(), l1)))
                && (isPointInLine(l2.getStart(), l1) && isPointInLine(l2.getEnd(), l1))) {
            return Adjacency.PARTIAL;
        }
        return Adjacency.NOT;
    }

    /**
     * Determines if one Point is at the start or the end of the Line
     * @param point
     * @param line
     * @return
     */
    private boolean isPointInLineBoundsExclusive(Point point, Line line) {
        return !arePointsEqualsWithAccuracy(point, line.getStart())
                && !arePointsEqualsWithAccuracy(point, line.getEnd())
                && isPointInLineBounds(point, line);
    }

    /**
     * Determine if two points are near enough to be equals
     * @param p1
     * @param p2
     * @return
     */
    private boolean arePointsEqualsWithAccuracy(Point p1, Point p2) {
        return isAccurate(p1.getX(), p2.getX())
                && isAccurate(p1.getY(), p2.getY());
    }

    /**
     * Determines if the Point is in the Line
     * @param point
     * @param line
     * @return
     */
    private boolean isPointInLineBounds(Point point, Line line) {
        if (line.getStart().getX() <= point.getX() && point.getX() <= line.getEnd().getX()
            || line.getEnd().getX() <= point.getX() && point.getX() <= line.getStart().getX()) {
            if(line.getStart().getY() <= point.getY() && point.getY() <= line.getEnd().getY()
                || line.getEnd().getY() <= point.getY() && point.getY() <= line.getStart().getY()) {
                return isPointInLine(point, line);
            }
        }
        return false;
    }

    /**
     * Use a Line to define a function and mathematically determines if the point is in the line.
     * @param point
     * @param line
     * @return
     */
    private boolean isPointInLine(Point point, Line line) {
        if ((line.getEnd().getX()-line.getStart().getX()) != 0) {
            double m = (line.getEnd().getY() - line.getStart().getY()) / (line.getEnd().getX() - line.getStart().getX());
            double b = m * line.getStart().getX()*(-1) + line.getStart().getY();
            double x = point.getX();
            double y = m * x + b;
            return isAccurate(point.getY(), y);
        }
        return isAccurate(line.getStart().getX(), point.getX());
    }

    private Point getYValueForXInLine(double x, Line line){
        if ((line.getEnd().getX()-line.getStart().getX()) != 0) {
            double m = (line.getEnd().getY() - line.getStart().getY()) / (line.getEnd().getX() - line.getStart().getX());
            double b = m * line.getStart().getX()*(-1) + line.getStart().getY();
            double y = m * x + b;
            if (line.getStart().getX() < x && x < line.getEnd().getX()
                    || line.getEnd().getX() < x && x < line.getStart().getX()) {
                return new Point(x, y);
            }
        }
        return null;
    }

    /**
     * Gets the accuracy fom application.properties file and compares the numbers
     * @param a First number
     * @param b Second number
     * @return how near are both numbers using the accuracy
     */
    private boolean isAccurate(Double a, Double b) {
        return Math.abs(a - b) < 1.0/Math.pow(10, rectanglesApplicationUtilities.getAccuracy());
    }

    @Autowired
    public void setRectanglesApplicationUtilities(RectanglesApplicationUtilities rectanglesApplicationUtilities) {
        this.rectanglesApplicationUtilities = rectanglesApplicationUtilities;
    }

}
