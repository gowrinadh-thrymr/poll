package com.auro.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auro.beans.ActivePoll_Bean;
import com.auro.beans.Poll;
import com.auro.beans.PollOption_Bean;
import com.auro.beans.Polls_Bean;
import com.auro.beans.VoteCount;
import com.auro.service.ConnectionService;
import com.auro.service.PollService;

@Service
public class PollServiceImpl implements PollService {

	@Autowired
	ConnectionService connectionService;


	@Override
	public ActivePoll_Bean getActivePollData() {
		System.out.println("GETTING ACTIVE POLL DETAILS");
		int pollid = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivePoll_Bean bean = new ActivePoll_Bean();
		List<PollOption_Bean> list = new ArrayList<PollOption_Bean>();
		Connection conn = null;
		try {
			conn = connectionService.getJNDIConnection();
			if (conn != null) {
				pstmt = conn.prepareStatement("select * from ADMINISTRATOR.POLLS where STATUS = 'active'");
				rs = pstmt.executeQuery();
				if (rs != null) {
					Polls_Bean pollsBean = new Polls_Bean();
					while (rs.next()) {
						pollid = rs.getInt(1);
						pollsBean.setPollQuestion(rs.getString(2));
						pollsBean.setPollId(pollid);
						bean.setPollsBean(pollsBean);
					}
				}
				pstmt = null;
				rs = null;
				pstmt = conn.prepareStatement("select * from ADMINISTRATOR.POLLOPTION where pollid = " + pollid + "");
				rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						PollOption_Bean polloption = new PollOption_Bean();
						polloption.setPollOptions(rs.getString(3));
						polloption.setId(rs.getInt(1));
						list.add(polloption);
					}
					bean.setPollOptions(list);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}

	@Override
	public void saveVoteData(String pollId, String user, String optionId) {
		PreparedStatement pstmt = null;
		int success = 0;
		Connection conn = null;
		try {
			String insertquery = "insert into ADMINISTRATOR.polldata values(?,?,?)";
			conn = connectionService.getJNDIConnection();
			if (conn != null) {
				pstmt = conn.prepareStatement(insertquery);
				if (pstmt != null) {
					pstmt.setInt(1, Integer.parseInt(pollId));
					pstmt.setString(2, user);
					pstmt.setInt(3, Integer.parseInt(optionId));
					success = pstmt.executeUpdate();
				}
			}
			if (success != 0) {
				System.out.println("Success fully poll submited");
			} else {
				System.out.println("Poll is not submited");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();

				if (pstmt != null)
					pstmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Poll> getPolls(String user) {
		System.out.println("Enter into getPolls dao");
		List<Poll> listOfPolls = new ArrayList<Poll>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		/* , b.option_type, b.option_image */
		String pollQuery = "select a.pollid,a.pollquetion,b.ID,b.POLLOPTIONS from "
				+ "ADMINISTRATOR.polls a,ADMINISTRATOR.polloption b where a.POLLID = b.POLLID and a.status = 'active'  and  "
				+ " a.POLLID not in (select POLLID from ADMINISTRATOR.POLLDATA where upper(USERID)=upper('" + user
				+ "'))" + " order by a.CREATEDDATE desc";

		try {
			conn = connectionService.getJNDIConnection();
			if (conn != null) {
				pstmt = conn.prepareStatement(pollQuery);
				if (pstmt != null) {
					rs = pstmt.executeQuery();
				}
				Poll poll = null;
				if (rs != null) {
					while (rs.next()) {
						poll = new Poll();
						poll.setPollId(rs.getInt(1));
						poll.setQname(rs.getString(2));
						poll.setId(rs.getString(3));
						poll.setOption(rs.getString(4));
						/*
						 * poll.setOptionType(rs.getString(5));
						 * System.out.println("=======================" + rs.getString(5)); if (null !=
						 * rs.getBlob(6)) { Blob blob = rs.getBlob(6); byte[] imgData = null; imgData =
						 * blob.getBytes(1, (int) blob.length());
						 * poll.setOptionImage("data:image/jpeg;base64," + Base64.encode(imgData)); }
						 */
						listOfPolls.add(poll);
					}
				}
			}
			System.out.println("End getPolls dao");
		} catch (SQLException e) {
			System.out.println("Exception @@@ " + e);
		} catch (Exception e) {
			System.out.println("Exception @@@ " + e);
		} finally {
			try {
				if (conn != null)
					conn.close();

				if (pstmt != null)
					pstmt.close();

				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listOfPolls;
	}

	@Override
	public List<Map<String, String>> getQuestionsCount() {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String countQuery = "select a.pollid ,count(1) pollsCount from ADMINISTRATOR.POLLDATA a,ADMINISTRATOR.POLLS b where b.STATUS='active' and a.POLLID=b.POLLID group by a.pollid";
		// String countQuery = "select pollid ,count(1) pollsCount from
		// DB2ADMIN.POLLOPTION group by pollid";
		try {
			conn = connectionService.getJNDIConnection();
			if (conn != null) {
				pstmt = conn.prepareStatement(countQuery);
				rs = pstmt.executeQuery();
				Map<String, String> pollMap;
				if (rs != null) {
					while (rs.next()) {
						pollMap = new HashMap<String, String>();
						pollMap.put(rs.getString("pollid"), rs.getString("pollsCount"));

						list.add(pollMap);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception @@@ " + e);
		} finally {
			try {
				if (conn != null)
					conn.close();

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<VoteCount> displayVotes(Map<Integer, Integer> pollCounts, String user) {
		List<VoteCount> list = new ArrayList<VoteCount>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			/*
			 * String displayQry =
			 * "select  b.*,a.option_image from ADMINISTRATOR.POLLOPTION a," +
			 * "(SELECT POLLOPTION.POLLID,polls.POLLQUETION,POLLOPTION.ID,POLLOPTION.POLLOPTIONS, "
			 * +
			 * "count(POLLDATA.OPTIONID) optioncount, POLLOPTION.option_type,polls.CREATEDDATE FROM ADMINISTRATOR.POLLDATA "
			 * +
			 * "RIGHT JOIN ADMINISTRATOR.POLLOPTION ON POLLDATA.OPTIONID=POLLOPTION.ID inner join ADMINISTRATOR.POLLS "
			 * +
			 * "ON POLLS.POLLID=POLLOPTION.POLLID WHERE POLLOPTION.POLLID IN(SELECT POLLDATA.POLLID FROM "
			 * + "ADMINISTRATOR.POLLDATA WHERE upper(USERID)=upper('" + user +
			 * "') AND POLLID IN(SELECT POLLID FROM " +
			 * "ADMINISTRATOR.POLLS WHERE POLLS.STATUS = 'active')) group by polls.POLLQUETION,POLLOPTION.ID,POLLOPTION.POLLID,"
			 * +
			 * "POLLOPTION.POLLOPTIONS,POLLOPTION.option_type,polls.CREATEDDATE) b where a.POLLID = b.POLLID and a.ID =b.ID "
			 * + "order by b.CREATEDDATE desc";
			 */

			String displayQry = "select  b.* from ADMINISTRATOR.POLLOPTION a,"
					+ "(SELECT ADMINISTRATOR.POLLOPTION.POLLID,ADMINISTRATOR.polls.POLLQUETION,ADMINISTRATOR.POLLOPTION.ID,ADMINISTRATOR.POLLOPTION.POLLOPTIONS,"
					+ "count(ADMINISTRATOR.POLLDATA.OPTIONID) optioncount, ADMINISTRATOR.polls.CREATEDDATE FROM ADMINISTRATOR.POLLDATA "
					+ "RIGHT JOIN ADMINISTRATOR.POLLOPTION ON ADMINISTRATOR.POLLDATA.OPTIONID=ADMINISTRATOR.POLLOPTION.ID inner join ADMINISTRATOR.POLLS "
					+ "ON ADMINISTRATOR.POLLS.POLLID=ADMINISTRATOR.POLLOPTION.POLLID WHERE ADMINISTRATOR.POLLOPTION.POLLID IN(SELECT ADMINISTRATOR.POLLDATA.POLLID FROM "
					+ "ADMINISTRATOR.POLLDATA WHERE upper(USERID)=upper('" + user
					+ "') AND POLLID IN(SELECT POLLID FROM "
					+ "ADMINISTRATOR.POLLS WHERE ADMINISTRATOR.POLLS.STATUS = 'active')) group by ADMINISTRATOR.polls.POLLQUETION,ADMINISTRATOR.POLLOPTION.ID,ADMINISTRATOR.POLLOPTION.POLLID,"
					+ "ADMINISTRATOR.POLLOPTION.POLLOPTIONS,ADMINISTRATOR.polls.CREATEDDATE) b where a.POLLID = b.POLLID and a.ID =b.ID "
					+ "order by b.CREATEDDATE desc";

			conn = connectionService.getJNDIConnection();
			if (conn != null) {
				pstmt = conn.prepareStatement(displayQry);
				if (pstmt != null)
					rs = pstmt.executeQuery();
			}
			VoteCount voteData = null;
			if (rs != null) {
				while (rs.next()) {
					voteData = new VoteCount();
					voteData.setPollId(rs.getInt(1));
					voteData.setQuestionName(rs.getString(2));
					voteData.setOptionId(rs.getInt(3));
					voteData.setOptionName(rs.getString(4));
					voteData.setCount(rs.getInt(5));
					/*
					 * voteData.setOptionType(rs.getString(6));
					 * 
					 * if ("image".equals(rs.getString(6))) {
					 * 
					 * Blob blob = rs.getBlob(8); byte[] imgData = null; imgData = blob.getBytes(1,
					 * (int) blob.length()); voteData.setOptionImage("data:image/jpeg;base64," +
					 * Base64.encode(imgData));
					 * 
					 * // voteData.setOptionImage(getBlobOptionAsBase64(rs.getInt(3), conn)); }
					 */

					list.add(voteData);
					if (pollCounts.get(voteData.getPollId()) == null) {
						pollCounts.put(voteData.getPollId(), voteData.getCount());
					} else {
						pollCounts.put(voteData.getPollId(),
								voteData.getCount() + pollCounts.get(voteData.getPollId()));
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("Exception @@@ " + e);
		} finally {
			try {
				if (conn != null)
					conn.close();

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}




}
