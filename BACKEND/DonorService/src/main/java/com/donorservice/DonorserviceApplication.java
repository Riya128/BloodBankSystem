package com.donorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
 

@SpringBootApplication
@EnableEurekaClient

public class DonorserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonorserviceApplication.class, args);
	}
	
	

}
