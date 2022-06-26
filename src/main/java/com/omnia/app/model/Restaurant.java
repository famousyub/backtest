package com.omnia.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "restaurants")
public class Restaurant extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String restoname;
	
	private String adress  ;
	
	private String phonenumber ;
	
	private String slug ;
	
	
	
	@Lob 
	
	private String photoUrl;
	
	
	
	
	  public Restaurant(long id, String restoname, String adress, String phonenumber, String slug, String photoUrl,
			Company company, List<Recipe> recipes, List<TableResto> tablesresto) {
		super();
		this.id = id;
		this.restoname = restoname;
		this.adress = adress;
		this.phonenumber = phonenumber;
		this.slug = slug;
		this.photoUrl = photoUrl;
		this.company = company;
		this.recipes = recipes;
		this.tablesresto = tablesresto;
	}


	public List<TableResto> getTablesresto() {
		return tablesresto;
	}


	public void setTablesresto(List<TableResto> tablesresto) {
		this.tablesresto = tablesresto;
	}


	public Restaurant(long id, String restoname, String adress, String phonenumber, String slug, String photoUrl,
			Company company, List<Recipe> recipes) {
		super();
		this.id = id;
		this.restoname = restoname;
		this.adress = adress;
		this.phonenumber = phonenumber;
		this.slug = slug;
		this.photoUrl = photoUrl;
		this.company = company;
		this.recipes = recipes;
	}


	public String getPhotoUrl() {
		return photoUrl;
	}


	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}


	  @JsonIgnore
	  @ManyToOne(fetch = FetchType.LAZY, optional = false) 
	  @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
	  private Company company;
	  
	  
		 @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
			@Fetch(FetchMode.SELECT)
			@JsonIgnore
			private List<Recipe> recipes = new ArrayList<>();
		 
		 
		 
		 
		 @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
		 @Fetch(FetchMode.SELECT)
		 @JsonIgnore
		 private List<TableResto> tablesresto = new ArrayList<>();
	  
	 


	public List<Recipe> getRecipes() {
			return recipes;
		}


		public void setRecipes(List<Recipe> recipes) {
			this.recipes = recipes;
		}


	public Restaurant(long id, String restoname, String adress, String phonenumber, String slug, Company company,
				List<Recipe> recipes) {
			super();
			this.id = id;
			this.restoname = restoname;
			this.adress = adress;
			this.phonenumber = phonenumber;
			this.slug = slug;
			this.company = company;
			this.recipes = recipes;
		}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getRestoname() {
		return restoname;
	}


	public void setRestoname(String restoname) {
		this.restoname = restoname;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getSlug() {
		return slug;
	}


	public void setSlug(String slug) {
		this.slug = slug;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public Restaurant(long id, String restoname, String adress, String phonenumber, String slug, Company company) {
		super();
		this.id = id;
		this.restoname = restoname;
		this.adress = adress;
		this.phonenumber = phonenumber;
		this.slug = slug;
		this.company = company;
	}


	public Restaurant() {
		super();
	}
	  
	  
	

}
