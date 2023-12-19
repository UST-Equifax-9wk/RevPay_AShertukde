package com.Revature.RevPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RevPayApplication {

	public static void main(String[] args) {
		ApplicationContext iocContainer = SpringApplication.run(RevPayApplication.class, args);
	}

}
