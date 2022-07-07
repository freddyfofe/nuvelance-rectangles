package com.faforerof.rectangles.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleView implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(ConsoleView.class);

    public void run(String... args) throws Exception {
        LOG.info("***** Starting Application *****");
        LOG.info("***** Closing Application *****");
    }
}
