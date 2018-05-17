package com.auro.beans;

import java.io.Serializable;
import java.util.List;

public class ActivePoll_Bean implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private Polls_Bean pollsBean;

	private List<PollOption_Bean> pollOptions;

	public Polls_Bean getPollsBean() {
		return pollsBean;
	}

	public void setPollsBean(Polls_Bean pollsBean) {
		this.pollsBean = pollsBean;
	}

	public List<PollOption_Bean> getPollOptions() {
		return pollOptions;
	}

	public void setPollOptions(List<PollOption_Bean> pollOptions) {
		this.pollOptions = pollOptions;
	}

}
