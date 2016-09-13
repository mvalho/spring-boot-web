package com.mvalho.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mvalho.controller.HomeController;
import com.mvalho.entity.Person;
import com.mvalho.repository.PersonRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = PersonRepository.class)
@EntityScan(basePackageClasses = Person.class)
@ComponentScan(basePackageClasses={HomeController.class})
public class AppConfig {

	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class);
	}
}
