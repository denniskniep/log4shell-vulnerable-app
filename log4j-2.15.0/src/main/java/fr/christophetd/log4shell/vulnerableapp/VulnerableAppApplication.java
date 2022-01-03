package fr.christophetd.log4shell.vulnerableapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VulnerableAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VulnerableAppApplication.class, args);
		System.out.println("log4j Version:" + org.apache.logging.log4j.LogManager.class.getPackage().getImplementationVersion());
	}

}
