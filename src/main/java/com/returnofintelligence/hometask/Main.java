package com.returnofintelligence.hometask;

import com.returnofintelligence.hometask.core.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by The Diamond Doge on 16.11.2017.
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        new Application().applicationStart();
    }
}
