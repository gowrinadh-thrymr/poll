package com.auro.service;

import java.util.List;
import java.util.Map;

import com.auro.beans.ActivePoll_Bean;
import com.auro.beans.Poll;
import com.auro.beans.VoteCount;

public interface PollService {

	ActivePoll_Bean getActivePollData();

	void saveVoteData(String questionid, String user, String optionid);

	List<Poll> getPolls(String user);

	List<Map<String, String>> getQuestionsCount();

	List<VoteCount> displayVotes(Map<Integer, Integer> pollCounts, String user);

}
