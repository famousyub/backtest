package com.omnia.app.payload;

public class EquipementResponse extends EquipmentListResponse {

	private String login;

	private String password;

	private String ipAddress;
	
	private boolean alarm;
	
	

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getIpAddress() {
		return ipAddress;
	}

	@Override
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
