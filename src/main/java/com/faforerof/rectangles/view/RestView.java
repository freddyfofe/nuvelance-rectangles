package com.faforerof.rectangles.view;

import com.faforerof.rectangles.entities.Adjacency;
import com.faforerof.rectangles.services.RectangleOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestView {

    @Autowired
    RectangleOperation rectangleOperation;

    @GetMapping("/intersects")
    public ResponseEntity<Boolean> intersects(@RequestBody RectanglesRequestWrapper rectangles) {
        Boolean result = rectangleOperation.intersects(rectangles.getRectangleOne(), rectangles.getRectangleTwo());
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @GetMapping("/contains")
    public ResponseEntity<Boolean> contains(@RequestBody RectanglesRequestWrapper rectangles) {
        Boolean result = rectangleOperation.contains(rectangles.getRectangleOne(), rectangles.getRectangleTwo());
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @GetMapping("/adjacent")
    public ResponseEntity<Adjacency> adjacent(@RequestBody RectanglesRequestWrapper rectangles) {
        Adjacency result = rectangleOperation.adjacent(rectangles.getRectangleOne(), rectangles.getRectangleTwo());
        return new ResponseEntity<Adjacency>(result, HttpStatus.OK);
    }
}
