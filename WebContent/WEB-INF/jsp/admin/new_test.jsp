<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title><fmt:message key="new_test.title" /></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<jsp:include page="../parts/header.jsp" />
	<div id="wraper">
		<jsp:include page="../parts/menu.jsp" />
		<div id="content">
			<h3>
				<fmt:message key="new_test.h3" />
			</h3>
			<form action="controller?command=doCreateTest" method="post">
				<table>
					<tr>
						<td><fmt:message key="new_test.testName" /></td>
						<td><input type="text" maxlength="100" name="testName"
							value="${testName}" /> <c:if test="${not empty errorTestName}">
								<br />
								<span class="error_label">${errorTestName}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="new_test.subjectName" /></td>
						<td><input type="text" maxlength="50" name="subjectName"
							value="${subjectName}" /> <c:if
								test="${not empty errorSubjectName}">
								<br />
								<span class="error_label">${errorSubjectName}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="new_test.timeToPass" /></td>
						<td><input type="text" maxlength="8" name="timeToPass"
							value="${timeToPass}" /> <c:if
								test="${not empty errorTimeToPass}">
								<br />
								<span class="error_label">${errorTimeToPass}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="edit_test.complexity" /></td>
						<td><input type="text" maxlength="8" name="complexity"
							value="${complexity}" /> <c:if
								test="${not empty errorComplexity}">
								<br />
								<span class="error_label">${errorComplexity}</span>
							</c:if></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit"
							value="<fmt:message key="new_test.create"/>" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../parts/footer.jsp" />
</body>
</html>