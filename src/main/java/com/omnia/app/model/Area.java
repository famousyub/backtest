package com.omnia.app.model;

import java.io.Serializable;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "area")
public class Area  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	@Column(name="level",nullable=true,columnDefinition = "integer default 0")
	private Integer level ;

	
	   private String name;
	   
	   
	   public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}


	public Area(Long id, Integer level, String name, byte[] areapic, Company company, Set<ATable> atable) {
		super();
		this.id = id;
		this.level = level;
		this.name = name;
		this.areapic = areapic;
		this.company = company;
		this.atable = atable;
	}


	@Lob
	   @Column(name="areapic",nullable = true,updatable=true)
	   private byte[] areapic;
	   

	    public byte[] getAreapic() {
		return areapic;
	}


	public void setAreapic(byte[] areapic) {
		this.areapic = areapic;
	}


		public Area(Long id, String name, byte[] areapic, Company company, Set<ATable> atable) {
		super();
		this.id = id;
		this.name = name;
		this.areapic = areapic;
		this.company = company;
		this.atable = atable;
	}


		@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "company_id", nullable = false)
	    @JsonIgnore
	    private Company company;
	    
	    
	    public Area() {
			super();
		}


		public Area(Integer level, String name) {
			super();
			this.level = level;
			this.name = name;
		}

       // @JsonIgnore
		@OneToMany(mappedBy = "area", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	    private Set<ATable> atable;


	public Area(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public Area(String name) {
		super();
		this.name = name;
	}



	public Set<ATable> getAtable() {
		return atable;
	}


	public void setAtable(Set<ATable> atable) {
		this.atable = atable;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public Area(Long id, String name, Company company, Set<ATable> atable) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.atable = atable;
	}


	public Area(String name, Company company, Set<ATable> atable) {
		super();
		this.name = name;
		this.company = company;
		this.atable = atable;
	}


}
