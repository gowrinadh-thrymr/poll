package com.auro.beans;

import java.io.Serializable;

public class PollData_Bean implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private int optionId;

	private int count;

	private String pollOptions;

	private String pollQuestion;

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPollOptions() {
		return pollOptions;
	}

	public void setPollOptions(String pollOptions) {
		this.pollOptions = pollOptions;
	}

	public String getPollQuestion() {
		return pollQuestion;
	}

	public void setPollQuestion(String pollQuestion) {
		this.pollQuestion = pollQuestion;
	}

}
