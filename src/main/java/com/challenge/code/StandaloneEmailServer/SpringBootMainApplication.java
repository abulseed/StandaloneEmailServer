package com.challenge.code.StandaloneEmailServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main class configured for running SpringApplication
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SpringBootMainApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootMainApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMainApplication.class, args);
	}
}
