package br.com.vrosendo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ValdeFlixApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValdeFlixApplication.class, args);
	}
}
