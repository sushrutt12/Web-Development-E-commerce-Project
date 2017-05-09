package com.my.spring.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="admin_table")
@PrimaryKeyJoinColumn(name="personID")
public class Admin extends Person {
	
	
	

	public Admin() {
	this.setRole("Admin");
	}

	
	
}