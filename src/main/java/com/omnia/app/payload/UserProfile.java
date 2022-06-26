package com.omnia.app.payload;

import java.time.Instant;

public class UserProfile {
	
	private Long id;
	private String username;
	private String firstName;
	private String lastname;
	private Instant joinedAt;
	private Long pollCount;
	private Long voteCount;

	public UserProfile(Long id, String username, String firstName, String lastname, Instant joinedAt, Long pollCount, Long voteCount) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastname = lastname;
		this.joinedAt = joinedAt;
		this.pollCount = pollCount;
		this.voteCount = voteCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Instant getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(Instant joinedAt) {
		this.joinedAt = joinedAt;
	}

	public Long getPollCount() {
		return pollCount;
	}

	public void setPollCount(Long pollCount) {
		this.pollCount = pollCount;
	}

	public Long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Long voteCount) {
		this.voteCount = voteCount;
	}
}