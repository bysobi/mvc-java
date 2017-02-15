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
			<form action="controller?command=doRegisterUser" method="post"><br/>
				f_name_eng: <input type="text" name="f_name_eng"><br/>
				s_name_eng: <input type="text" name="s_name_eng"><br/>
				m_name_eng: <input type="text" name="m_name_eng"><br/>
				f_name_ukr: <input type="text" name="f_name_ukr"><br/>
				s_name_ukr: <input type="text" name="s_name_ukr"><br/>
				m_name_ukr: <input type="text" name="m_name_ukr"><br/>
				department: <input type="text" name="department"><br/>
				email: <input type="text" name="email"><br/>
				phone: <input type="text" name="phone"><br/>
				sec_email: <input type="text" name="sec_email"><br/>
				password: <input type="text" name="password"><br/>
				confirm password: <input type="text" name="confirm_password"><br/>
				<button type="submit">Ебануть эту хуйню</button>
			</form>
		</div>
	</div>
	<jsp:include page="parts/footer.jsp" />

</body>
</html>