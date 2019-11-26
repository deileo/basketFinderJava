package com.deileo.basketFinderJava;

import com.deileo.basketFinderJava.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableJpaAuditing
public class BasketFinderJavaApplication {
	public static void main(String[] args) {
		SpringApplication.run(BasketFinderJavaApplication.class, args);
	}
}
