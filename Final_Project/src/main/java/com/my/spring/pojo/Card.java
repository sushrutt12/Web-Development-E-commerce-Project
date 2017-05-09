package com.my.spring.pojo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="card_table")
@PrimaryKeyJoinColumn(name="cardID")

public class Card {

	private long cardID;
	private String nameOnCard;
	private String cardNumber;
	private int expirationMonth;
	private int expirationYear;
	@ManyToOne
	private Customer customer;
	}
