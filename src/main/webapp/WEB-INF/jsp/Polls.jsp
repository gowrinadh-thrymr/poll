<%-- 
    Document   : Polls
    Created on : Mar 30, 2016, 3:11:27 PM
    Author     : tempuser
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<portlet:defineObjects />

<link href="<%=request.getContextPath()%>/themes/base/jquery.ui.all.css"
	rel="stylesheet" type="text/css" />
<!--         <img src="<%=request.getContextPath()%>/images/pbar-ani.gif"/>-->
<script src="<%=request.getContextPath()%>/js/ui/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
<script
	src="<%=request.getContextPath()%>/js//ui/jquery.ui.progressbar.js"></script>



<script>
	function validatePollForm(radiobtname) {

		if ($('input[name=' + radiobtname + ']:checked').length <= 0) {
			alert("select your option");
			return false;
		}

	}

	$(function() {
		$(".VotedPolls").children("div.bars").each(function() {
			$("#" + this.id).progressbar({
				value : parseInt(this.title)
			});
		});
	});
</script>

<%-- 	<portlet:renderURL var="renderCreatepollMethodURL">
		<portlet:param name="action" value="createpoll" />
	</portlet:renderURL> --%>
<portlet:actionURL var="renderCreatepollMethodURL">
	<portlet:param name="action" value="createpoll" />
</portlet:actionURL>
<portlet:actionURL var="submitPollMethodURL">
	<portlet:param name="action" value="submit-Poll" />
</portlet:actionURL>

<html>
<head>
<title>Calender Events</title>
<style type="text/css">

.ui-progressbar-value {
	background-image: url(<%=request.getContextPath()%>/images/pbar-ani.gif);
	margin: 0px !important;
	border: none !important;
}

.polls_form, .VotedPolls {
	border: 1px solid #CFCFCF;
	color: #5E5E5F;
	font-weight: bold;
	margin-bottom: 10px;
	padding: 10px 5px;
	text-align: left;
	width: 100%;
	margin-top: 10px;
	box-shadow: 5px 5px 5px #CFCFCF;
}

#polldataclass .pollheader p, .VotedPolls p {
	border-bottom: 1px solid #CFCFCF;
	margin: 0 0 15px;
	padding-bottom: 10px;
	text-align: left;
}

.ui-corner-all {
	height: 15px;
	margin-top: 5px;
	border-radius: 0px;
}

.poll-result {
	position: relative;
}

.poll-percent {
	position: absolute;
	right: 0px;
}

.polloptions {
	background: none repeat scroll 0 0 #ECEDEF;
	margin-bottom: 5px;
}

.vote-butn {
	background: none repeat scroll 0 0 #4D90FE;
	border: 0 none;
	color: #FFFFFF;
	font-weight: bold;
	left: 70%;
	padding: 5px 10px;
	position: relative;
}
</style>
</head>
<body>
	<div class="col-md-12 cstm-polls">
		<div class="box box-danger Poll">
			<div class="box-header poll_bg">

				<h3 class="box-title">Poll</h3>
				<c:if test="${isPollMember ne false}">
					<div align="right">
						<label><a href="${renderCreatepollMethodURL}"
							class="btn btn-success pull-right">Create Polls</a></label>
					</div>
				</c:if>

				<c:set var="comparepollid" value="0"></c:set>
				<c:forEach var="pollData" items="${pollData}" varStatus="row">
					<c:choose>
						<c:when test="${comparepollid ne pollData.pollId}">
							<c:if test="${comparepollid ne 0}">
								<!--<input class="pull-right btn-danger poll_btn" type="submit" value="submit"/>-->
								<input class="submit_btn" type="submit" value="submit" />
								<input type="hidden" value="${comparepollid}" name="optionID" />
								<input type="hidden" value="${comparepollid}" name="qid" />
								</form>
							</c:if>
							<c:set var="comparepollid" value="${pollData.pollId}"></c:set>
							<form class="VotedPolls" action="${submitPollMethodURL}"
								method="post"
								onsubmit="return validatePollForm('${pollData.pollId}')">
								<ul>
									<li class="li_question"><c:out value="${pollData.qname}" /></li>
								</ul>


								<label><input type="radio" name="${pollData.pollId}"
									value="${pollData.id}"> <c:choose>
										<c:when test="${pollData.optionType eq 'image'}">
											<img src="${pollData.optionImage}"
												style="max-width: 75px; height: 50px;" />
										</c:when>
										<c:otherwise>
											<c:out value="${pollData.option}" />
										</c:otherwise>
									</c:choose> </label><br />
						</c:when>
						<c:otherwise>
							<label> <input type="radio" name="${pollData.pollId}"
								value="${pollData.id}"> <c:choose>
									<c:when test="${pollData.optionType eq 'image'}">
										<img src="${pollData.optionImage}"
											style="max-width: 75px; height: 50px;" />
									</c:when>
									<c:otherwise>
										<c:out value="${pollData.option}" />
									</c:otherwise>
								</c:choose>
							</label>
							<br />
						</c:otherwise>
					</c:choose>
				</c:forEach>




				<c:if test="${comparepollid ne 0}">
					<!--<input class="pull-right btn-danger poll_btn" type="submit" value="submit"/>-->
					<input class="submit_btn" type="submit" value="submit" />
					<input type="hidden" value="${comparepollid}" name="optionID" />
					<input type="hidden" value="${comparepollid}" name="qid" />
					</form>
				</c:if>

				<!-------------------Display Vote Data--------------------->


				<c:set var="optionsCount" value="0" />

				<c:set var="questionCount" value="0" />
				<c:set var="pollivard" value="0" />

				<c:forEach var="voteObj" items="${votesData}" varStatus="row">
					<c:choose>
						<c:when test="${not (pollivard eq voteObj.pollId)}">

							<c:forEach var="tempPoll" items="${questioncount}">
								<c:forEach var="entry" items="${tempPoll}" varStatus="status">
									<fmt:parseNumber var="optionKey" integerOnly="true"
										type="number" value="${entry.key}" />
									<c:if test="${optionKey == voteObj.pollId}">
										<fmt:parseNumber var="countValue" integerOnly="true"
											type="number" value="${entry.value}" />
										<c:set var="optionsCount" value="${countValue}" />
									</c:if>

								</c:forEach>

							</c:forEach>

							<c:if test="${not(pollivard eq 0)}">
								<br />
								<br />
			</div>
			</c:if>
			<c:set var="questionCount" value="${questionCount+1}" />
			<div id="container"
				class="VotedPolls paginationIndex${questionCount}">

				<p>
					<c:out value="${voteObj.questionName}" />
				</p>
				<div class="poll-result">
					<span class="poll-option"> <c:choose>
							<c:when test="${voteObj.optionType eq 'image'}">
								<img src="${voteObj.optionImage}"
									style="max-width: 75px; height: 50px;" />
							</c:when>
							<c:otherwise>
								<c:out value="${voteObj.optionName}" />
							</c:otherwise>
						</c:choose>
					</span> <span class="poll-percent"> <fmt:formatNumber
							value="${((voteObj.count)/optionsCount)* 100 }"
							maxFractionDigits="0" />%
					</span>
					<c:set var="pollivard" value="${voteObj.pollId}" />
				</div>
				<div class="bars" id="${voteObj.optionId}"
					title="${((voteObj.count)/optionsCount)* 100 }"></div>

				</c:when>
				<c:otherwise>
					<div class="poll-result">
						<span class="poll-option"> <c:choose>
								<c:when test="${voteObj.optionType eq 'image'}">
									<img src="${voteObj.optionImage}"
										style="max-width: 75px; height: 50px;" />
								</c:when>
								<c:otherwise>
									<c:out value="${voteObj.optionName}" />
								</c:otherwise>
							</c:choose>
						</span> <span class="poll-percent"> <fmt:formatNumber
								value="${((voteObj.count)/optionsCount)* 100 }"
								maxFractionDigits="0" />%
						</span>
					</div>
					<div class="bars" id="${voteObj.optionId}"
						title="${((voteObj.count)/optionsCount)* 100 }"></div>

				</c:otherwise>
				</c:choose>
				<br />



				</c:forEach>
				<c:if test="${not(pollivard eq 0)}">
			</div>
			</c:if>



		</div>
	</div>

</body>
</html>



