package com.omnia.app.payload;

import javax.validation.constraints.*;

public class NetworkRequest {

	@NotBlank
	@Size(min = 4, max = 100)
	private String reference;

	@NotBlank
	@Size(min = 4, max = 100)
	private String country;

	@DecimalMin(value = "-90")
	@DecimalMax(value = "90")
	private double gpsLat;

	@DecimalMin(value = "-180")
	@DecimalMax(value = "180")
	private double gpsLong;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getGpsLat() {
		return gpsLat;
	}

	public void setGpsLat(double gpsLat) {
		this.gpsLat = gpsLat;
	}

	public double getGpsLong() {
		return gpsLong;
	}

	public void setGpsLong(double gpsLong) {
		this.gpsLong = gpsLong;
	}

}