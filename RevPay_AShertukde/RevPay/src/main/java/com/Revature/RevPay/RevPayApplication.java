package com.Revature.RevPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {
		"com.Revature.RevPay.services",
		"com.Revature.RevPay.controllers",
		"com.Revature.RevPay.models",
		"com.Revature.RevPay.repositories",
		"com.Revature.RevPay.config"
})
public class RevPayApplication {

	public static void main(String[] args) {
		ApplicationContext iocContainer = SpringApplication.run(RevPayApplication.class, args);
	}

}
