package com.mvalho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "contact_id")
	private Long id;
	
	@Column(name = "contact_type")
	private ContactType type;
	
	private String value;
	
	@ManyToOne
	@JoinColumn(name="person_id", nullable=false)
	private Person person;
	
	public Contact() {}
	
	public Contact(ContactType type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ContactType getType() {
		return type;
	}
	public void setType(ContactType type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Contact [type = ");
		stringBuilder.append(this.type);
		stringBuilder.append(", value = ");
		stringBuilder.append(this.value);
		stringBuilder.append("] ");
		
		return stringBuilder.toString();
	}
}
