package com.legacy.practice.step3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.legacy.practice.step3.mapper")
@SpringBootApplication
public class LegacyPracticeStep2Application {

	public static void main(String[] args) {
		SpringApplication.run(LegacyPracticeStep2Application.class, args);
	}

}
