package com.macheal.app.prospect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProspectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProspectApplication.class, args);
    }

}
