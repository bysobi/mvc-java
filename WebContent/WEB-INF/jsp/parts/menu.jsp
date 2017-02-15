<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="menu">
	<div id="menu_left">
		<h1>
			<fmt:message key="menu.logo" />
		</h1>
		<div class="menu_item">
			<a href="."><fmt:message key="menu.main" /></a>
		</div>
		<c:if test="${aut_user.role == 'ROLE_ADMIN'}">
			<div class="menu_item">
				<a href="controller?command=listTest"><fmt:message
						key="menu.list_tests" /></a>
			</div>
			<div class="menu_item">
				<a href="controller?command=listUsers"><fmt:message
						key="menu.list_users" /></a>
			</div>
			<div class="menu_item">
				<a href="controller?command=getResultTest"><fmt:message
						key="menu.list_results" /></a>
			</div>
		</c:if>
		<c:if test="${aut_user.role == 'ROLE_STUDENT'}">
			<div class="menu_item">
				<a href="controller?command=usersListTest"><fmt:message
						key="menu.list_tests" /></a>
			</div>
			<div class="menu_item">

				<!-- CABINET -->
				<a href="controller?command=userInfo"><fmt:message
						key="menu.users_privateOffice" /></a>
			</div>
		</c:if>
		<div style="clear: both;"></div>
	</div>

	<div id="menu_right">
		<a><img alt="" src="img/logo_testing.png" /></a>
	</div>
	<div style="float: none; clear: both;"></div>
</div>