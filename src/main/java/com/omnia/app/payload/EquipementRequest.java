package com.omnia.app.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EquipementRequest {

	@NotBlank
	@Size(min = 2, max = 100)
	private String name;

	@NotBlank
	@Size(min = 4, max = 100)
	private String login;

	@NotBlank
	@Size(min = 4, max = 100)
	private String password;

	@NotBlank
	@Size(min = 7, max = 100)
	private String ipAddress;
	
	private boolean alarm;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public boolean isAlarm() {
		return alarm;
	}

	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}
	
	

}