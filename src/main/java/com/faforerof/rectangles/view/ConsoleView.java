package com.faforerof.rectangles.view;

import com.faforerof.rectangles.entities.Rectangle;
import com.faforerof.rectangles.services.RectangleOperation;
import com.faforerof.rectangles.services.implementation.RectangleOperationLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"com.faforerof.rectangles"})
public class ConsoleView implements CommandLineRunner {

    @Autowired
    RectangleOperationLocal rectangleOperationLocal;

    private static Logger LOG = LoggerFactory.getLogger(ConsoleView.class);

    public void run(String... args) throws Exception {
        LOG.info("***** Starting Application *****");
        Rectangle r1 = new Rectangle(0.0,0.0, 10.0, 10.0);
        Rectangle r2 = new Rectangle(11.0,10.0, 5.0, 5.0);
        rectangleOperationLocal.adjacent(r1,r2);
        LOG.info("***** Closing Application *****");
    }
}
