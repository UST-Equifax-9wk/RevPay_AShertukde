package com.Revature.RevPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {
		"com.Revature.RevPay.services",
		"com.Revature.RevPay.controllers",
		"com.Revature.RevPay.models",
<<<<<<< HEAD:RevPay_AShertukde/RevPay/src/main/java/com/Revature/RevPay/RevPayApplication.java
		"com.Revature.RevPay.repositories",
		"com.Revature.RevPay.config"
=======
		"com.Revature.RevPay.repositories"
>>>>>>> origin:RevPay/src/main/java/com/Revature/RevPay/RevPayApplication.java
})
public class RevPayApplication {

	public static void main(String[] args) {
		ApplicationContext iocContainer = SpringApplication.run(RevPayApplication.class, args);
	}

}
