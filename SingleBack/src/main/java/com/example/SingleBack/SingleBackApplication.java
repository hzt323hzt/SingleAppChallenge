package com.example.SingleBack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@MapperScan("com.example.SingleBack.dao")
@SpringBootApplication
public class SingleBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingleBackApplication.class, args);
	}

}
