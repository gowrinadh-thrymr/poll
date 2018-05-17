package com.auro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.naming.NamingException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.auro.beans.Poll;
import com.auro.beans.PollData_Bean;
import com.auro.beans.VoteCount;
import com.auro.service.PollService;
import com.auro.validations.PollValidation;
import com.ibm.portal.um.Group;
import com.ibm.portal.um.PumaHome;
import com.ibm.portal.um.PumaLocator;
import com.ibm.portal.um.PumaProfile;
import com.ibm.portal.um.User;
import com.ibm.portal.um.exceptions.PumaAttributeException;
import com.ibm.portal.um.exceptions.PumaMissingAccessRightsException;
import com.ibm.portal.um.exceptions.PumaModelException;
import com.ibm.portal.um.exceptions.PumaSystemException;

@Controller
@RequestMapping(value = "VIEW")
public class PollController {

	@Autowired
	PollService pollService;


	List<PollData_Bean> list = new ArrayList<PollData_Bean>();

	Map<String, Integer> m = new HashMap<String, Integer>();

	private PumaHome pumaHome = null;

	@PostConstruct
	public void init() {
		try {
			if (pumaHome == null) {
				javax.naming.Context ctx = new javax.naming.InitialContext();
				pumaHome = (PumaHome) ctx.lookup(PumaHome.JNDI_NAME);
			}
		} catch (NamingException e) {
			System.out.println("Excecption==" + e);

		}
	}

	@RenderMapping
	public String defaultView(ModelMap map, RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		System.out.println("========default");
		String isPollMemberGroup = null;
		boolean isPollMember = false;
		System.out.println("Enter into polls display view");
		try {
			User currentUser = pumaHome.getProfile().getCurrentUser();
			PumaProfile pumaProfile = pumaHome.getProfile();
			// PumaController pumaController = pumaHome.getController();
			PumaLocator pumaLocator = pumaHome.getLocator();
			isPollMemberGroup = (String) request.getPortletSession().getAttribute("isPollsMemberGroup");
			if (isPollMemberGroup == null || "".equals(isPollMemberGroup)) {
				isMemberOfGroup(request, pumaLocator, pumaProfile, currentUser);
			}
			if (request.getPortletSession().getAttribute("isPollsMemberGroup") != null
					&& "Yes".equals((String) request.getPortletSession().getAttribute("isPollsMemberGroup"))) {
				isPollMember = true;
				// Logger.Parle.debug("IsPoll Member " + isPollMember);
			}
			request.setAttribute("isPollMember", isPollMember);
		} catch (Exception e) {
			// Logger.Parle.debug("Polls member exception " + e);
		}

		List<Poll> polls = pollService.getPolls(request.getRemoteUser());
		List<Map<String, String>> questioncount = pollService.getQuestionsCount();
		Map<Integer, Integer> pollCounts = new HashMap<Integer, Integer>();
		List<VoteCount> voteData = pollService.displayVotes(pollCounts, request.getRemoteUser());
		System.out.println("pollData======== " + polls);
		map.addAttribute("pollData", polls);
		map.addAttribute("pollDataSize", polls.size());
		map.addAttribute("votesData", voteData);
		map.addAttribute("pollCounts", pollCounts);
		map.addAttribute("questioncount", questioncount);
		return "Polls";
	}

	@ActionMapping(params = "action=createpoll")
	public void createPoll(ActionRequest request, ActionResponse response) {
		System.out.println("========createpoll");
//		List<Questions> allPollQuestions = pollService.getAllPollQuestions();
//		map.addAttribute("allQuestions", allPollQuestions);
//		return "createpoll";
		System.out.println("EVENT SETTING =================");
		response.setEvent("createpoll", "createpoll");
	}

/*	@ActionMapping(params = "action=create-Poll")
	public void createPoll(ActionRequest request, ActionResponse response) {
		System.out.println("In Creating Poll");
		String question = request.getParameter("pollQuestion");
		List options = Arrays.asList(request.getParameterValues("option"));
		String user = request.getRemoteUser();
//		pollService.savePollData(question, options, user,"text");
//		response.setRenderParameter("action", "createpoll");
		response.setEvent("createpoll", "createpoll");
	}*/

	@ActionMapping(params = "action=submit-Poll")
	public void saveVoteData(ActionRequest request, ActionResponse response) {
		System.out.println("Submitting Poll");
		// String questionId = request.getParameter("question_id");
		// String optionId = request.getParameter("optionId");
		String user = request.getRemoteUser();
		String questionId = request.getParameter("qid");
		String optionId = request.getParameter(request.getParameter("optionID"));
		if (PollValidation.isValidVotingData(questionId, optionId, user)) {
			pollService.saveVoteData(questionId, user, optionId);
		}
		response.setRenderParameter("action", "home");
	}

/*	@ActionMapping(params = "action=questionStatusChange")
	public void changeStatusOfPoll(ActionRequest request, ActionResponse response) {
		String currentStatus = request.getParameter("currentStatus");
		String questionID = request.getParameter("questionID");
		pollService.statusChange(currentStatus, Integer.parseInt(questionID));
		response.setRenderParameter("action", "createpoll");
	}*/

	public static void isMemberOfGroup(RenderRequest request, PumaLocator pumaLocator, PumaProfile pumaProfile,
			User currentUser) {
		boolean isMember = false;
		// Logger.Parle.debug("Entered into isMemberOfGroup of polls");
		try {
			List<Group> findGroupsByPrincipal = pumaLocator.findGroupsByPrincipal(currentUser, true);
			List GROUP_ATTRS = new ArrayList<String>(1);
			GROUP_ATTRS.add("cn");
			for (Group group : findGroupsByPrincipal) {
				Map attributeMap = pumaProfile.getAttributes(group, GROUP_ATTRS);
				for (Iterator it = attributeMap.values().iterator(); it.hasNext();) {
					Object obj = it.next();
					String groupCN = null;
					if (obj instanceof List) {
						List test = (ArrayList) obj;
						for (Iterator iterator = test.iterator(); iterator.hasNext();) {
							groupCN = (String) iterator.next();

							System.out.println("-- isMember() List > groupCN: " + groupCN);

						}
					} else if (obj instanceof String) {
						groupCN = (String) obj;

						System.out.println("-- isMember() String > groupCN: " + groupCN);

					}

					// if ("HR".equals(groupCN)) {
					if ("wpsadmins".equals(groupCN)) {
						isMember = true;
						request.getPortletSession().setAttribute("isPollsMemberGroup", "Yes");
					}

				}

			}
			if (!isMember)
				request.getPortletSession().setAttribute("isPollsMemberGroup", "No");

		} catch (PumaSystemException e) {
			// Logger.Parle.debug("Exception in member group " + e);
			e.printStackTrace();
		} catch (PumaModelException e) {
			// Logger.Parle.debug("Exception in member group " + e);
			e.printStackTrace();
		} catch (PumaMissingAccessRightsException e) {
			// Logger.Parle.debug("Exception in member group " + e);
			e.printStackTrace();
		} catch (PumaAttributeException e) {
			// Logger.Parle.debug("Exception in member group " + e);
			e.printStackTrace();
		}

		// return isMember;
	}
}
