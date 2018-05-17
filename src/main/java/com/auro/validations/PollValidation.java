package com.auro.validations;

public class PollValidation {

	public static Boolean isValidVotingData(String qid, String oid, String user) {
		Boolean status = Boolean.FALSE;
		if (qid != null && oid != null && user != null) {
			if (!qid.trim().isEmpty() && !oid.trim().isEmpty() && !user.trim().isEmpty()) {
				status = Boolean.TRUE;
			}
		}
		return status;
	}
}
