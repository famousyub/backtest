package com.omnia.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omnia.app.enums.RoleName;
import com.omnia.app.enums.TableStatus;

@Entity
@Table(name="tablerestos")
public class TableResto extends DateAudit{
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Size(max = 140)
	@Column(unique = true)
	private String tablename;
	
	
	private Integer tablenumber;
	
	
	@Enumerated(EnumType.STRING)
	//@NaturalId
	@Column(length = 60)
	private TableClass tableclass;
	
	
	@Enumerated(EnumType.STRING)
	//@NaturalId
	@Column(length = 60)
	private TableStatus tableavaible;
	

	
	
	
	public TableResto(long id, @Size(max = 140) String tablename, Integer tablenumber, TableClass tableclass,
			TableStatus tableavaible, Restaurant restaurant) {
		super();
		this.id = id;
		this.tablename = tablename;
		this.tablenumber = tablenumber;
		this.tableclass = tableclass;
		this.tableavaible = tableavaible;
		this.restaurant = restaurant;
	}





	public TableStatus getTableavaible() {
		return tableavaible;
	}





	public void setTableavaible(TableStatus tableavaible) {
		this.tableavaible = tableavaible;
	}





	@ManyToOne(fetch = FetchType.LAZY, optional = false) 
	 @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
	 @JsonIgnore
	 private Restaurant restaurant;





	public long getId() {
		return id;
	}





	public void setId(long id) {
		this.id = id;
	}





	public String getTablename() {
		return tablename;
	}





	public void setTablename(String tablename) {
		this.tablename = tablename;
	}





	public Integer getTablenumber() {
		return tablenumber;
	}





	public void setTablenumber(Integer tablenumber) {
		this.tablenumber = tablenumber;
	}





	public TableClass getTableclass() {
		return tableclass;
	}





	public void setTableclass(TableClass tableclass) {
		this.tableclass = tableclass;
	}





	public Restaurant getRestaurant() {
		return restaurant;
	}





	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}





	public TableResto(long id, @Size(max = 140) String tablename, Integer tablenumber, TableClass tableclass,
			Restaurant restaurant) {
		super();
		this.id = id;
		this.tablename = tablename;
		this.tablenumber = tablenumber;
		this.tableclass = tableclass;
		this.restaurant = restaurant;
	}





	public TableResto() {
		super();
	}
	
	
	
	
}
