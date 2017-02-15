<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset= utf-8" />
<title><fmt:message key="add_to_test.title" /></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<jsp:include page="../parts/header.jsp" />
	<div id="wraper">
		<jsp:include page="../parts/menu.jsp" />
		<div id="content">
			<h3>
				<fmt:message key="add_to_test.h3" />
			</h3>
			<form action="controller?command=doAddingQuestionAndAnswers"
				method="post">
				<fmt:message key="add_to_test.selectTest" />
				<input type="hidden" name="question_id" value="${id}" /> <select
					size="1" name="testId">
					<option value="<fmt:message key="add_to_test.chooseTheTest " />">${test.testName}</option>
					<c:forEach items="${tests}" var="test">
						<option value="${test.testName}">${test.testName}</option>
					</c:forEach>

				</select>
				<table>
					<tr>
						<td><fmt:message key="add_to_test.tesxtOfQuestion" /></td>
						<td><textarea rows="5" cols="40" name="textOfQuestion"></textarea>
							<c:if test="${not empty errorTextOfQuestion}">
								<br />
								<span class="error_label">${errorTextOfQuestion}</span>
							</c:if></td>
					</tr>

					<tr>
						<td><fmt:message key="add_to_test.textOfAnswer" /></td>
						<td><input type="hidden" name="answerNumber" value="{1}" />
							<input type="text" maxlength="300" name="textOfAnswerNumber1"
							value="${textOfAnswer}" /> <c:if
								test="${not empty errorTextOfAnswer1}">
								<br />
								<span class="error_label">${errorTextOfAnswer1}</span>
							</c:if> <input type="checkbox" name="flag1" value="true"
							<c:if test="${not empty flag1 && flag1}"> checked </c:if> /> <fmt:message
								key="add_to_test.answerFlag" /></td>
					</tr>
					<tr>
						<td><fmt:message key="add_to_test.textOfAnswer" /></td>
						<td><input type="hidden" name="answerNumber" value="{1}" />
							<input type="text" maxlength="300" name="textOfAnswerNumber2"
							value="${textOfAnswer}" /> <c:if
								test="${not empty errorTextOfAnswer2}">
								<br />
								<span class="error_label">${errorTextOfAnswer2}</span>
							</c:if> <input type="checkbox" name="flag2" value="true"
							<c:if test="${not empty flag2 && flag2}"> checked </c:if> /> <fmt:message
								key="add_to_test.answerFlag" /></td>
					</tr>
					<tr>
						<td><fmt:message key="add_to_test.textOfAnswer" /></td>
						<td><input type="hidden" name="answerNumber" value="{1}" />
							<input type="text" maxlength="300" name="textOfAnswerNumber3"
							value="${textOfAnswer}" /> <c:if
								test="${not empty errorTextOfAnswer3}">
								<br />
								<span class="error_label">${errorTextOfAnswer3}</span>
							</c:if> <input type="checkbox" name="flag3" value="true"
							<c:if test="${not empty flag3 && flag3}"> checked </c:if> /> <fmt:message
								key="add_to_test.answerFlag" /></td>
					</tr>
					<tr>
						<td><fmt:message key="add_to_test.textOfAnswer" /></td>
						<td><input type="hidden" name="answerNumber" value="{1}" />
							<input type="text" maxlength="300" name="textOfAnswerNumber4"
							value="${textOfAnswer}" /> <c:if
								test="${not empty errorTextOfAnswer4}">
								<br />
								<span class="error_label">${errorTextOfAnswer4}</span>
							</c:if> <input type="checkbox" name="flag4" value="true"
							<c:if  test="${not empty flag4 && flag4}"> checked </c:if> /> <fmt:message
								key="add_to_test.answerFlag" /></td>
					</tr>
					<tr>
						<c:if test="${not empty errorSelectingTest}">
							<br />
							<span class="error_label">${errorSelectingTest}</span>
						</c:if>
					</tr>
					<tr>
						<td></td>
						<c:if test="${not empty errorFlag}">
							<br />
							<span class="error_label">${errorFlag}</span>
						</c:if>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit"
							value="<fmt:message key="add_to_test.addQuestionAndAnswers"/>" /></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	<jsp:include page="../parts/footer.jsp" />
</body>
</html>