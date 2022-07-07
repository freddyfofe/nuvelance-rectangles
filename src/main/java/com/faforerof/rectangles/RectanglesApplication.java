package com.faforerof.rectangles;

import com.faforerof.rectangles.view.ConsoleView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RectanglesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleView.class, args);
    }
}
