package com.omnia.app.entity;

public class Notification {
	
	
	private String notifname ;
	private String notiftype;
	private String notifTable;
	private Integer count ;
	public String getNotifname() {
		return notifname;
	}
	public void setNotifname(String notifname) {
		this.notifname = notifname;
	}
	public String getNotiftype() {
		return notiftype;
	}
	public void setNotiftype(String notiftype) {
		this.notiftype = notiftype;
	}
	public String getNotifTable() {
		return notifTable;
	}
	public void setNotifTable(String notifTable) {
		this.notifTable = notifTable;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	public void increment() {
		 this.count++;
	}
	public Notification() {
		super();
	}
	
	

}
