<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="header">
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<div class="container">
		<div class="row">
			<div class="col-md-4 text-center left">
				<div class="auth">
					<c:if test="${not empty aut_user}">
						<fmt:message key="header.entered_like" /> ${aut_user.email}
					</c:if> <c:if test="${empty aut_user}">
						<fmt:message key="header.unauthorized" />
					</c:if>
				</div>
			</div>
			<div class="col-md-4 text-center middle">
				
			</div>
			<div class="col-md-4 text-center right">
				<c:if test="${not empty aut_user}">
						<a href="controller?command=logout"><fmt:message
								key="header.logout" /></a>
					</c:if> <c:if test="${empty aut_user}">
						<a href="controller?command=login"><fmt:message
								key="header.login" /></a>
					</c:if> <c:if test="${empty aut_user}">
						<a href="controller?command=registerUser"><fmt:message
								key="header.registration" /></a>
					</c:if>
			</div>
		</div>
	</div>
</div>