package com.omnia.app.model;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	
	private String productname ;
	private String description ;
	
	private  Double price ;
	
	
	private Integer orderproduct;
	
	 @ManyToOne(fetch = FetchType.LAZY, optional = false) 
	  @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = true)
	  private Category category;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getOrderproduct() {
		return orderproduct;
	}

	public void setOrderproduct(Integer orderproduct) {
		this.orderproduct = orderproduct;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product(long id, String productname, String description, Double price, Integer orderproduct,
			Category category) {
		super();
		this.id = id;
		this.productname = productname;
		this.description = description;
		this.price = price;
		this.orderproduct = orderproduct;
		this.category = category;
	}

	public Product() {
		super();
	}
	 
	 
	 
	 

}
