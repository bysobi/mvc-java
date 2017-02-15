<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title><fmt:message key="test_start" /></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<jsp:include page="../parts/header.jsp" />
	<div id="wraper">
		<jsp:include page="../parts/menu.jsp" />
		<div id="content">
			<h3>
				<fmt:message key="test_start.h3" />
			</h3>
			<form action="controller?command=doPassingTest" method="post">

				<table>

					<c:forEach items="${questionsList}" var="question">
						<h3>
							<c:out value="${question.textOfQuestion }" />
						</h3>
						<c:forEach items="${listAnswers}" var="answer">

							<%-- 	< <input type="checkbox" name="answerNumber" value="true"
								<c:if test="${not empty flag && flag}" > checked </c:if> />
						 --%>
							<c:if test="${question.id == answer.qustionId }">
								<input type="checkbox" name="answerNumber" value="${answer.answerNumber}"
									<c:if test="${not empty flag && flag}" > checked </c:if> />
								<fmt:message key="${answer.textOfAnswer }" />
								<br>


							</c:if>



							<%-- 	<input type="checkbox" name="answerNumber2" value="${answerNumber}"
								<c:if test="${not empty flag2 && flag2}"> checked </c:if> />
							<fmt:message key="${answer.textOfAnswer }" />
							<br>
							<input type="checkbox" name="answerNumber3" value="true"
								<c:if test="${not empty flag3 && flag3}"> checked </c:if> />
							<fmt:message key="${answer.textOfAnswer }" />
							<br>
							<input type="checkbox" name="answerNumberF4" value="true"
								<c:if test="${not empty flag4 && flag4}"> checked </c:if> />
							<fmt:message key="${answer.textOfAnswer }" />
							<br> --%>


						</c:forEach>

					</c:forEach>
					<tr>
						<td><input type="submit"
							value="<fmt:message key="test_submit"/>" />
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../parts/footer.jsp" />
</body>
</html>