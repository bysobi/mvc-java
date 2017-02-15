<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title><fmt:message key="registration.title" /></title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/select2.css">
	<link rel="stylesheet" type="text/css" href="css/style1.css">
	<link rel="stylesheet" type="text/css" href="css/switchery.min.css">
    <script src="js/jquery/jquery-2.1.1.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- <script type="text/javascript" src="js/jquery.transliterate.js"></script>
	<script type="text/javascript" src="js/jquery.transliterate.ua.js"></script> -->
	<script type="text/javascript" src="http://ukrlit.org/js/jquery.transliterate.js"></script>
	<script type="text/javascript" src="http://ukrlit.org/js/jquery.transliterate.ua.js"></script>
	<script type="text/javascript" src="js/select2.js"></script>
	<script type="text/javascript" src="js/switchery.min.js"></script>
</head>
<body>
	<jsp:include page="parts/header.jsp" />
	<div class="container">
		<div class="row text-center">
			<div class="col-md-4"></div>
			<div class="col-md-4">
			<form action="controller?command=doLogin" method="post">
				<div class="form-group">
				    <label for="input-name"><fmt:message key="login.login"/></label>
				    <input type="text" class="form-control" name="email" />
				</div>
				<div class="form-group">
				    <label for="input-name"><fmt:message key="login.pass"/></label>
				    <input type="password" class="form-control" name="password" />
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-primary">
				</div>
				<c:if test="${not empty errorMessage}">
					<br/>
					<span class="error_label">${errorMessage}</span>
				</c:if>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
	<jsp:include page="parts/footer.jsp" />
</body>
</html>