<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title><fmt:message key="welcome.title" /></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<jsp:include page="parts/header.jsp" />
	<div id="wraper">
		<jsp:include page="parts/menu.jsp" />
		<div id="content">
			
			<c:if test="${empty aut_user}">
				<h1>
					<fmt:message key="welcome.h1" />
				</h1>
				<p>
					<fmt:message key="welcome.p_text" />
				</p>
				<p>
					<fmt:message key="welcome.p_must_login" />
				</p>
				<hr />
			</c:if>
			<c:if test="${not empty aut_user}">
			<h1>
				<fmt:message key="welcome.h1.aut_user" />
			</h1>
			<p>
				<fmt:message key="welcome.p_text.aut_user" />
			</p></c:if>
			<%-- 	<p>
				<fmt:message key="welcome.p_must_login" />
			</p>
			<hr /> --%>
		</div>
	</div>
	<jsp:include page="parts/footer.jsp" />
</body>
</html>