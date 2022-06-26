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
@Table(name = "categories")
public class Category {

	private static final long serialVersionUID = -4143232651678461L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Size(max = 140)
	@Column(unique = true)
	private String name;

	private boolean shown;

	@Column(unique = true)
	private long level;

	@Lob
	private String imagePath;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	private List<Recipe> recipes = new ArrayList<>();

	 @JsonIgnore
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "company_id", referencedColumnName = "id",nullable = false) 
	 private Company company;
	 
	 
	
	 
	 
	 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
		@Fetch(FetchMode.SELECT)
		@JsonIgnore
		private List<Product> products = new ArrayList<>();
	

	public Category() {
		super();
	}

	public Category(long id, @Size(max = 140) String name, boolean shown, long level, String imagePath,
			List<Recipe> recipes) {
		super();
		this.id = id;
		this.name = name;
		this.shown = shown;
		this.level = level;
		this.imagePath = imagePath;
		this.recipes = recipes;

	}

	/*
	 * public Category(long id, @Size(max = 140) String name, boolean shown, String
	 * imagePath, int level) { super(); this.id = id; this.name = name; this.shown =
	 * shown; this.imagePath = imagePath; this.level = level; }
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isShown() {
		return shown;
	}

	public void setShown(boolean shown) {
		this.shown = shown;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long categoryRepLevel) {
		this.level = categoryRepLevel;
	}
	
	

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", shown=" + shown + ", level=" + level + ", imagePath="
				+ imagePath + ", recipes=" + recipes + "]";
	}

}