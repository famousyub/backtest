package com.omnia.app.payload;

import javax.validation.constraints.*;

public class CompanyRequest {

	@NotBlank
	@Size(min = 4, max = 100)
	private String name;

	@NotBlank
	@Size(min = 4, max = 100)
	private String language;
	
	@NotBlank
	@Size(min = 4, max = 100)
	private String logo;
	
	@NotBlank
	@Size(min = 4, max = 100)
	private String timeFormat;
	
	@NotBlank
	@Size(min = 4, max = 100)
	private String dateFormat;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	


}