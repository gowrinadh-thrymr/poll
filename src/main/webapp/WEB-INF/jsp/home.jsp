<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>

	<portlet:renderURL var="renderCreatepollMethodURL">
		<portlet:param name="action" value="createpoll" />
	</portlet:renderURL>


	<portlet:actionURL var="submitPollMethodURL">
		<portlet:param name="action" value="submit-Poll" />
	</portlet:actionURL>

	<%-- <a href="${renderCreatepollMethodURL}">Create Poll</a> --%>

	<div class="col-md-12">
		<div class="box box-danger Poll">
			<div class="box-header poll_bg">
				<h3 class="box-title">Poll</h3>
				<c:if test="${isPollMember ne false}">
					<div align="right">
						<label><a href="${renderCreatepollMethodURL}">Create
								Polls</a></label>
					</div>

				</c:if>
				<c:if test="${pollDataSize eq 0}">
					<div align="center">
						<span>Curently there was no polls active</span>
					</div>

				</c:if>
				<form action="${submitPollMethodURL}" method="post">

					<h4>Poll Question</h4>
					<ul>
						<li><c:out value="${activePollData.pollsBean.pollQuestion}" /></li>
					</ul>

					<h4>Poll Answers</h4>
					<c:forEach items="${activePollData.pollOptions}"
						var="retrieveOptionData">
						<label><input type="radio" name="optradio"
							value="${retrieveOptionData.id}"> <c:out
								value="${retrieveOptionData.pollOptions}" /></label>
						<br />
						<input type="hidden" name="optionId"
							value="${retrieveOptionData.id}" />
					</c:forEach>
					<input type="hidden" value="${activePollData.pollsBean.pollId}"
						name="question_id" /> <input
						class="pull-right btn-danger poll_btn" type="submit"
						value="submit" />
				</form>
			</div>
		</div>
	</div>

</body>
</html>