package fr.christophetd.log4shell.vulnerableapp;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

@RestController
public class MainController {

    private static final Logger logger = LogManager.getLogger("HelloWorld");

    @GetMapping("/")
    public String indexNew(@RequestHeader("X-Api-Version") String apiVersion) {
        ThreadContext.put("apiVersion", apiVersion);
        logger.info("Received a request");
        return "Hello, world from log4j " + org.apache.logging.log4j.LogManager.class.getPackage().getImplementationVersion();
    }

    @GetMapping("/old")
    public String indexOld(@RequestHeader("X-Api-Version") String apiVersion) {
        logger.info("Received a request for API version " + apiVersion);
        return "Hello, world from log4j " + org.apache.logging.log4j.LogManager.class.getPackage().getImplementationVersion();
    }

}