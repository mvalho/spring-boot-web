package com.mvalho.entity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "person_id")
	private Long id;
	
	@NotBlank
	private String name;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	private Calendar dateOfBirth;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="person", fetch = FetchType.EAGER) //Just be more convenient.
	private List<Contact> contacts;
	
	public Person() {}
	
	public Person(String name, Calendar dateOfBirth, Contact ... contacts) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.contacts = Arrays.asList(contacts);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Person [id = ");
		stringBuilder.append(this.id);
		stringBuilder.append(", name = ");
		stringBuilder.append(this.name);
		stringBuilder.append(", date of birth = ");
		stringBuilder.append(this.dateOfBirth.getTime());
		stringBuilder.append(", Contacts [");
		this.contacts.stream().forEach(c -> stringBuilder.append(c.toString()));
		stringBuilder.append("]");
		
		return stringBuilder.toString();
	}
}
