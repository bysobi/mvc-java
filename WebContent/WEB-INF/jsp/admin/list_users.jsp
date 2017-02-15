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
									<fmt:message key="list_users.email" />
								</div></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${departements}" var="departement">
							<tr>
								<td>${departement.name}</div></td>
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