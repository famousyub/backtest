package com.omnia.app.payload;

public class ApiResponse {

	private Boolean success;
	private Long id;
	private String message;

	public ApiResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	public ApiResponse(Boolean success, Long id, String message) {
		this.success = success;
		this.id = id;
		this.message = message;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}