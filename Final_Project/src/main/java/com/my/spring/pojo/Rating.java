package com.my.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="rating_table")

public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ratingID", unique = true, nullable = false)
	
private long ratingID;	
private int score;
private String title;
private String description;
private String postedBy;

@ManyToOne
@JsonBackReference
@JoinColumn(name="productID")
private Product product;

public Rating()
{}



public Product getProduct() {
	return product;
}



public void setProduct(Product product) {
	this.product = product;
}



public long getRatingId() {
	return ratingID;
}

public void setRatingId(long ratingId) {
	this.ratingID = ratingId;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getPostedBy() {
	return postedBy;
}

public void setPostedBy(String postedBy) {
	this.postedBy = postedBy;
}



}
