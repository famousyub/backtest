package com.omnia.app.payload;

public class FeedsListResponse {

	private String dateTime;

	private String userName;

	private String description;

	private Long elementID;

	private String elementType;

	public FeedsListResponse() {
		super();
	}

	public FeedsListResponse(String dateTime, String userName, String description, Long elementID, String elementType) {
		super();
		this.dateTime = dateTime;
		this.userName = userName;
		this.description = description;
		this.elementID = elementID;
		this.elementType = elementType;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getElementID() {
		return elementID;
	}

	public void setElementID(Long elementID) {
		this.elementID = elementID;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

}