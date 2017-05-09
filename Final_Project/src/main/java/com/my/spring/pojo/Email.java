package com.my.spring.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "email_table")
public class Email {
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="person"))
	private long id;

	private String emailAddress;
	@OneToOne
	@PrimaryKeyJoinColumn
	private Person person;

	public Email() {
	}

	public Email(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	


}
