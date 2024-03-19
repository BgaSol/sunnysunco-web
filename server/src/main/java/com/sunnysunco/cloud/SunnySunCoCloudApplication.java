package com.sunnysunco.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SunnySunCoCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(SunnySunCoCloudApplication.class, args);
        System.out.println("swagger-ui: http://localhost:" + 8080 + "/api/swagger-ui/index.html");
        System.out.println("api-doc: http://localhost:" + 8080 + "/api/api-docs");
    }
}
