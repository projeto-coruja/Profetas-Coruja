<%@page import="business.Bean.user.AuthBean"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login test</title>
</head>
<body>
<c:choose>
	<c:when test="${empty sessionScope['userLoginSuccessfull'] && !sessionScope['userLoginSuccessfull']}">
		<form method="post" action="/Profetas/doLogin">
			<fieldset>				
				<!-- Login -->
				<label class="sidebar" for="login">Login:</label>
				<input class="inputLogin" type="text" name="user" height="30px" size="auto" >
	
				<!-- Senha -->
				<label class="sidebar" for="senha">Senha:</label>
				<input class="inputLogin" type="password" name="password" height="30px" size="auto" >
			</fieldset>
	
	
				<input class="buttonEntrar" type="submit" name="Entrar"	value="Entrar" />
		</form>
	</c:when>
	<c:otherwise>
		olá ${sessionScope['userName']}!<br/>
		<form method="post" action="/Profetas/doLogout">
			<input class="buttonEntrar" type="submit" name="Logout"	value="logout" />
		</form>
	</c:otherwise>
</c:choose>
</body>
</html>