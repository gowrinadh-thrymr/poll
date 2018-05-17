package com.auro.beans;

import java.io.Serializable;

public class PollOption_Bean implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private int id;

	private String pollId;

	private String pollOptions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPollId() {
		return pollId;
	}

	public void setPollId(String pollId) {
		this.pollId = pollId;
	}

	public String getPollOptions() {
		return pollOptions;
	}

	public void setPollOptions(String pollOptions) {
		this.pollOptions = pollOptions;
	}
}
