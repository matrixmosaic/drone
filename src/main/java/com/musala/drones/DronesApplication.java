package com.musala.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {
	    "com.musala.drones.config",  
	    "com.musala.drones.service", 
	    "com.musala.drones.model",
	    "com.musala.drones.controller",
	    "com.musala.drones.repository"
	})
public class DronesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DronesApplication.class, args);
	}

}
