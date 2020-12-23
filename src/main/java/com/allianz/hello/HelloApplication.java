package com.allianz.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.allianz.hello.config.CodeCampConfig;

@EnableConfigurationProperties(CodeCampConfig.class)
@SpringBootApplication
public class HelloApplication {
	
	@Autowired
	private CodeCampConfig config;

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Hello Test Test Test");
			System.out.println("Language " + config.getLanguage());
			System.out.println("Student " + config.getStudent());
		};
	}

}
