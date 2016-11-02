package org.tallymed.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class RestApplication {
    public static void main(String[] args) {
       // SpringApplication.run(RestApplication.class, args);
    	startRestAPI(args);
    }
    public static void startRestAPI(String[] args) {
    	SpringApplication.run(RestApplication.class, args);
	}
}
