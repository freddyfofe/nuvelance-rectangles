package com.faforerof.rectangles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Line class used to define one of the sides of the rectangle class
 */
@Getter
@Setter
@AllArgsConstructor
public class Line {
    private Point start;
    private Point end;
}
