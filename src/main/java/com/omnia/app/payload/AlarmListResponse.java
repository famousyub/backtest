package com.omnia.app.payload;

import com.omnia.app.enums.AlarmLevel;

public class AlarmListResponse {

	private Long id;

	private AlarmLevel level;
	
	public AlarmListResponse() {
		super();
	}

	public AlarmListResponse(Long id, AlarmLevel level) {
		super();
		this.id = id;
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AlarmLevel getLevel() {
		return level;
	}

	public void setLevel(AlarmLevel level) {
		this.level = level;
	}

}
