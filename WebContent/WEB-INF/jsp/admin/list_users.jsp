<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title><fmt:message key="list_users.title" /></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<jsp:include page="../parts/header.jsp" />
	<div id="wraper">
		<jsp:include page="../parts/menu.jsp" />

		<div id="content">

			<c:if test="${not empty users}">
				<h2>
					<fmt:message key="list_users.h3" />
				</h2>
				<c:if test="${empty users}">
					<p>
						<fmt:message key="list_users.empty" />
					</p>
				</c:if>
				<table id="users_table">
					<thead>
						<tr>
							<th><div class="c1">
									<fmt:message key="list_users.user_lastName" />
								</div></th>
							<th><div class="c2">
									<fmt:message key="list_users.user_firstName" />
								</div></th>
							<th><div class="c3">
									<fmt:message key="list_users.user_secondName" />
								</div></th>
							<th><div class="c4">
									<fmt:message key="list_users.user_login" />
								</div></th>
						<%-- 	<th><div class="c5">
									<fmt:message key="list_users.user_activity" />
								</div></th> --%>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr title="<fmt:message key="list_users.edit"/>"
								onclick="window.location.href='controller?command=editUser&id=${user.id}'">
								<td><div class="c1">${user.firstName}</div></td>
								<td><div class="c2">${user.secondName}</div></td>
								<td><div class="c3">${user.lastName}</div></td>
								<td><div class="c4">${user.userLogin}</div></td>
					<%-- 			<td><div class="c5">${user.isActive}</div></td>
 --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
	<jsp:include page="../parts/footer.jsp" />
</body>
</html>