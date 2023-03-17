package com.herookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com.herookie")
@SpringBootApplication
public class StudentzApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentzApplication.class, args);
	}

}
