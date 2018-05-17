package com.auro.beans;

import java.io.Serializable;

public class Questions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String question;
	
	private int questionID;
	
	private String status;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
