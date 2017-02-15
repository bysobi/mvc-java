<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title><fmt:message key="list_tests.title" /></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<jsp:include page="../parts/header.jsp" />
	<div id="wraper">
		<jsp:include page="../parts/menu.jsp" />
		<div id="content">
			<h2>
				<fmt:message key="list_tests.h3" />
			</h2>
			<c:if test="${not empty tests}">
				<table id="tests_table">
					<thead>
						<tr>
							<th><div class="c1">
									<fmt:message key="list_tests.name" />
								</div></th>
							<th><div class="c2">
									<fmt:message key="list_tests.subjectName" />
								</div></th>
							<th><div class="c3">
									<fmt:message key="list_tests.timeToPass" />
								</div></th>
							<th><div class="c4">
									<fmt:message key="list_tests.complexity" />
								</div></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tests}" var="test">
							<tr >
								<c:if test="${aut_user.role == 'ROLE_ADMIN'}" > 
								 
								<tr title="<fmt:message key="list_tests.edit"/>" onclick="window.location.href='controller?command=editTest&id=${test.id}'">
								
								 </c:if>
								<td><div class="c1">${test.testName}</div></td>
								<td><div class="c2">${test.subjectName}</div></td>
								<td><div class="c3">${test.timeToPass}</div></td>
								<td><div class="c4">${test.complexity}</div></td>
								</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<c:if test="${empty tests}">
				<p>
					<fmt:message key="list_tests.empty" />
				</p>
			</c:if>
			<hr />
			<c:if test="${aut_user.role == 'ROLE_ADMIN'}">
				<p>
					<a href="controller?command=createTest"><fmt:message
							key="list_tests.create_test" /></a>
				</p>
				<p>
					<a href="controller?command=addingQuestionAndAnswers"> <fmt:message
							key="list_tests.addingQuestion" /></a>
				</p>
			</c:if>
		</div>
	</div>
	<jsp:include page="../parts/footer.jsp" />
</body>
</html>