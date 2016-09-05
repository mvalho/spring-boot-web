package com.mvalho.main;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mvalho.entity.Contact;
import com.mvalho.entity.ContactType;
import com.mvalho.entity.Person;
import com.mvalho.repository.PersonRepository;
import com.mvalho.service.impl.DumbServiceImpl;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = PersonRepository.class)
@EntityScan(basePackageClasses = Person.class)
@ComponentScan(basePackageClasses={DumbServiceImpl.class})
public class AppConfig implements CommandLineRunner {
	@Autowired
	private PersonRepository personRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Contact email = new Contact(ContactType.EMAIL, "mvalho@gmail.com");
		Contact cellphone = new Contact(ContactType.CELLPHONE, "+554199999999");
		Contact phone = new Contact(ContactType.PHONE, "+554133333333");
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1984, 9, 18);
		
		Person person = new Person("Marcelo Carvalho", dateOfBirth, email, cellphone, phone);
		email.setPerson(person);
		cellphone.setPerson(person);
		phone.setPerson(person);
		
		personRepository.saveAndFlush(person);
		
		Person savedPerson = personRepository.findOne(1L);
		
		System.out.println("------> " + savedPerson.toString());
	}

}
