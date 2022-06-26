package com.omnia.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "recipes")
public class Recipe {

	private static final long serialVersionUID = -4143232651678461L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Size(max = 140)
	@Column(unique = true)
	private String name;

	private String description;

	@Lob
	private String imagePath;

	private Boolean shown;

	private float price;

	private long level;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SELECT)
	private List<Ingredient> ingredients = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	@JsonIgnore
	private Category category;
	
	
	 @ManyToOne(fetch = FetchType.LAZY, optional = false) 
	 @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
	 @JsonIgnore
	 private Restaurant restaurant;
	 
	 
	 @Column(name="quantitymax")
	 private Integer qunatitymax=1 ;
	 
	 
	 

	public Recipe(long id, @Size(max = 140) String name, String description, String imagePath, Boolean shown,
			float price, long level, List<Ingredient> ingredients, Category category, Restaurant restaurant,
			Integer qunatitymax) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
		this.shown = shown;
		this.price = price;
		this.level = level;
		this.ingredients = ingredients;
		this.category = category;
		this.restaurant = restaurant;
		this.qunatitymax = qunatitymax;
	}

	public Integer getQunatitymax() {
		return qunatitymax;
	}

	public void setQunatitymax(Integer qunatitymax) {
		this.qunatitymax = qunatitymax;
	}

	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getShown() {
		return shown;
	}

	public void setShown(Boolean shown) {
		this.shown = shown;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", imagePath=" + imagePath
				+ ", shown=" + shown + ", price=" + price + ", level=" + level + "]";
	}

}