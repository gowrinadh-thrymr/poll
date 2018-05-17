package com.auro.beans;

public class Poll {

	private int pollId;
	private String qname;
	private String id;
	private String option;
	private String optionType;
	private String optionImage;

	public int getPollId() {
		return pollId;
	}

	public void setPollId(int pollId) {
		this.pollId = pollId;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public String getOptionImage() {
		return optionImage;
	}

	public void setOptionImage(String optionImage) {
		this.optionImage = optionImage;
	}

}
