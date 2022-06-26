package com.omnia.app.payload;

import javax.validation.constraints.NotBlank;

public class AreaRequest {
	@NotBlank
	private String name ; 
	@NotBlank
	private Integer level;
	public AreaRequest(String name, Integer level) {
		super();
		this.name = name;
		this.level = level;
	}
	public AreaRequest() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	

}
