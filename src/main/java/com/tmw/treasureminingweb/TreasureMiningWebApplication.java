package com.tmw.treasureminingweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Starting the application
 */
@SpringBootApplication
@RestController
public class TreasureMiningWebApplication {
    /**
     * Start the website in localhost:8080
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(TreasureMiningWebApplication.class, args);
    }

    @GetMapping
    public List<String> quickExample() {
        return Arrays.asList("Treasure", "Mining");
    }
}
