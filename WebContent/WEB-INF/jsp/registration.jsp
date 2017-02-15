<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title><fmt:message key="registration.title" /></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<jsp:include page="parts/header.jsp" />
	<div id="wraper">
		<div id="content">
			<h3>
				<fmt:message key="registration.h3" />
			</h3>
			<form action="controller?command=doRegisterUser" method="post">
				<table>
					<tr>
						<td><fmt:message key="registration.user_lastname" /></td>
						<td><input type="text" maxlength="50" name="lastName"
							value="${lastName}" /> <c:if test="${not empty errorLastName}">
								<br />
								<span class="error_label">${errorLastName}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="registration.user_firstname" /></td>
						<td><input type="text" maxlength="50" name="firstName"
							value="${firstName}" /> <c:if test="${not empty errorFirstName}">
								<br />
								<span class="error_label">${errorFirstName}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="registration.user_secondname" /></td>
						<td><input type="text" maxlength="50" name="secondName"
							value="${secondName}" /> <c:if
								test="${not empty errorSecondName}">
								<br />
								<span class="error_label">${errorSecondName}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="registration.user_login" /></td>
						<td><input type="text" maxlength="50" name="userLogin"
							value="${userLogin}" /> <c:if test="${not empty errorUserLogin}">
								<br />
								<span class="error_label">${errorUserLogin}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="registration.user_pass" /></td>
						<td><input type="text" maxlength="10" name="userPassword"
							value="${userPassword}" /> <c:if
								test="${not empty errorPassword}">
								<br />
								<span class="error_label">${errorPassword}</span>
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="registration.user_confpass" /></td>
						<td><input type="text" maxlength="10" name="passwordConfirm"
							value="${passwordConfirm}" /> <c:if
								test="${not empty errorPasswordConfirm}">
								<br />
								<span class="error_label">${errorPasswordConfirm}</span>
							</c:if> <c:if test="${not empty  errorPasswordsNotEquel}">

								<span class="error_label">${ errorPasswordsNotEquel}</span>
							</c:if></td>

					</tr>

					<tr>
						<td></td>
						<td><input type="submit"
							value="<fmt:message key="registration.user_confirm"/>" /></td>
					</tr>
				</table>
			</form>

		</div>
	</div>
	<jsp:include page="parts/footer.jsp" />

</body>
</html>