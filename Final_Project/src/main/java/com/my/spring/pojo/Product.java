package com.my.spring.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="product_table")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="productID", unique=true, nullable=false)
    private long id;
	
    private String title;
    private String status;
	private double price;
    private int quantity;
    private String description;
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private Set<Rating> ratingList=new HashSet<Rating>();
    
    @Transient
	private CommonsMultipartFile photo;
    @Column(name = "filename")
	private String filename;   
	
	@ManyToOne
	@JsonManagedReference
	private Retailer retailer;
	
	@ManyToMany(mappedBy="products")
	@JsonManagedReference
	private Set<Category> categories = new HashSet<Category>();
	@Transient
	int postedBy;

//    public Product(String title, String message, Retailer retailer, Category catergory) {
//        this.title = title;
//        this.description = description;
//        this.retailer= retailer;      
//    }

    public Product() {
    }

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}

	public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}

	
	public Set<Rating> getRatingList() {
		return ratingList;
	}

	public void setRatingList(Set<Rating> ratingList) {
		this.ratingList = ratingList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
    
  

   
}