package com.auro.beans;

import java.io.Serializable;

public class Polls_Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pollId;

	private String pollQuestion;

	private String creator;

	private String createdDate;

	private String expiredDate;

	private String status;

	public int getPollId() {
		return pollId;
	}

	public void setPollId(int pollId) {
		this.pollId = pollId;
	}

	public String getPollQuestion() {
		return pollQuestion;
	}

	public void setPollQuestion(String pollQuestion) {
		this.pollQuestion = pollQuestion;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
