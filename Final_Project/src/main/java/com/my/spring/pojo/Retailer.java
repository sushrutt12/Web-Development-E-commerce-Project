package com.my.spring.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="retailer_table")
@PrimaryKeyJoinColumn(name="personID")
public class Retailer extends Person {
	
	
	

	public Retailer() {
		this.setRole("Retailer");
	}

	
	
}