<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="menu">
	<div id="menu_left">
		<h1>
			<fmt:message key="menu.cabinet" />
		</h1>

		<div class="menu_item">
			<a href="."><fmt:message key="menu.main" /></a>
		</div>
		<div>
			<h3>
				<fmt:message key="edit_user.user_lastName" />
				${aut_user.firstName}
				<fmt:message key="edit_user.user_firstName" />
				${aut_user.secondName}
				<fmt:message key="edit_user.user_secondName" />
				${aut_user.lastName}
			</h3>
		</div>

		<div style="clear: both;"></div>
	</div>

	<div id="menu_right">
		<a><img alt="" src="img/logo_testing.png" /></a>
	</div>
	<div style="float: none; clear: both;"></div>
</div>