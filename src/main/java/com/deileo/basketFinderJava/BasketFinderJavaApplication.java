package com.deileo.basketFinderJava;

import com.deileo.basketFinderJava.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class BasketFinderJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketFinderJavaApplication.class, args);
	}

}
