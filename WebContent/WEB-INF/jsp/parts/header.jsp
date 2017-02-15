<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="header">
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<table>
		<tr>
			<td><fmt:message key="header.lang"/> : <a href="controller?command=setLocale&locale=en">EN</a> | <a href="controller?command=setLocale&locale=ru">RU</a></td>
			<td><c:if test="${not empty aut_user}">
					<fmt:message key="header.entered_like" /> ${aut_user.userLogin}
				</c:if> <c:if test="${empty aut_user}">
					<fmt:message key="header.unauthorized" />
				</c:if> <a> </a></td>
			<td><c:if test="${not empty aut_user}">
					<a href="controller?command=logout"><fmt:message
							key="header.logout" /></a>
				</c:if> <c:if test="${empty aut_user}">
					<a href="controller?command=login"><fmt:message
							key="header.login" /></a>
				</c:if> <c:if test="${empty aut_user}">
					<a href="controller?command=registerUser"><fmt:message
							key="header.registration" /></a>
				</c:if></td>
		</tr>
	</table>
</div>