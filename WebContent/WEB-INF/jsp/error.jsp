<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />
<title>Ошибка</title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<jsp:include page="parts/header.jsp" />
	<div id="wraper">
		<div id="content">
			<h2>${errorMessage}</h2>
			<p><a href="."><fmt:message key="error.back"/></a></p>
		</div>
	</div>
	<jsp:include page="parts/footer.jsp" />
</body>
</html>