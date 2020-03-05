package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletException;

@SpringBootApplication
public class DemoApplication  extends SpringBootServletInitializer {

	public void onStartup(SpringApplicationBuilder application) throws ServletException {
		SpringApplicationBuilder builder=application.sources(DemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
