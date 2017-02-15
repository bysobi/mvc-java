<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title><fmt:message key="login.title"/></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<jsp:include page="parts/header.jsp" />
	<div id="wraper">
		<div id="content">
			<div>
			<div id="login">
				<form action="controller?command=doLogin" method="post">
						<table>
							<tr>
								<td><fmt:message key="login.login"/></td>
								<td><input type="text" name="login" /></td>
							</tr>
							<tr>
								<td><fmt:message key="login.pass"/></td>
								<td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="<fmt:message key="login.enter"/>" /></td>
							</tr>
						</table>
					<c:if test="${not empty errorMessage}">
						<br/>
						<span class="error_label">${errorMessage}</span>
					</c:if>
				</form>
			</div>
			</div>
		</div>
	</div>
	<jsp:include page="parts/footer.jsp" />
</body>
</html>