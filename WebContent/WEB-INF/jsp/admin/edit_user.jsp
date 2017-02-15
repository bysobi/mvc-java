<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title><fmt:message key="edit_user.title" /></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<jsp:include page="../parts/header.jsp" />
	<div id="wraper">
		<jsp:include page="../parts/menu.jsp" />
		<div id="content">
			<h3>
				<fmt:message key="edit_user.h3" />
			</h3>
			<form action="controller?command=doEditUser" method="post">
				<input type="hidden" name="id" value="${id}" />
				<table>
					<tr>
						<td><fmt:message key="edit_user.user_lastName" /></td>
						<td><input type="text" maxlength="50" name="lastName"
							value="${lastName}" /> <c:if test="${not empty errorLastName}">
								<br />
								<span class="error_label">${errorLastName}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="edit_user.user_firstName" /></td>
						<td><input type="text" maxlength="50" name="firstName"
							value="${firstName}" /> <c:if test="${not empty errorFirstName}">
								<br />
								<span class="error_label">${errorFirstName}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="edit_user.user_secondName" /></td>
						<td><input type="text" maxlength="50" name="secondName"
							value="${secondName}" /> <c:if
								test="${not empty errorSecondName}">
								<br />
								<span class="error_label">${errorSecondName}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="edit_user.user_login" /></td>
						<td><input type="text" maxlength="50" name="userLogin"
							value="${userLogin}" /> <c:if test="${not empty errorLogin}">
								<br />
								<span class="error_label">${errorLogin}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="edit_user.user_password" /></td>
						<td><input type="text" maxlength="50" name="userPassword"
							value="${userPassword}" /> <c:if test="${not empty errorUserPassword}">
								<br />
								<span class="error_label">${errorUserPassword}</span>
							</c:if></td>
					</tr>

					<tr>
						<td><fmt:message key="edit_user.user_activity" /></td>
						<td><label><input type="checkbox" name="isActive"
								<c:if test="${not empty isActive && isActive}">
									checked 
								</c:if> />
							<fmt:message key="edit_user.user_active" /></label></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit"
							value="<fmt:message key="edit_user.save_changes"/>" /></td>
					</tr>
				</table>
			</form>
			<hr />
			<p>
				<a href="controller?command=removeUser&id=${id}"><fmt:message
						key="edit_user.del_user" /></a>
			</p>
		</div>
	</div>
	<jsp:include page="../parts/footer.jsp" />
</body>
</html>