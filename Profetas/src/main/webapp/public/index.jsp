<%@page import="business.Bean.user.AuthBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% AuthBean auth = new AuthBean(); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login test</title>
</head>
<body>
<% if(!auth.isLoggedIn(session)){%>
	<form method="post" action="/Profetas/doLogin">
				<fieldset>				
					<!-- Login -->
					<label class="sidebar" for="login">Login:</label>
					<input class="inputLogin" type="text" name="user" height="30px" size="auto" placeholder="">

					<!-- Senha -->
					<label class="sidebar" for="senha">Senha:</label>
					<input class="inputLogin" type="password" name="password" height="30px" size="auto" placeholder="">
				</fieldset>


					<input class="buttonEntrar" type="submit" name="Entrar"	value="Entrar" />
	</form>

<%} else { %>
	olá <% out.print((String)session.getAttribute("userName")); %>!<br/>
	<form method="post" action="/Profetas/doLogout">
		<input class="buttonEntrar" type="submit" name="Logout"	value="logout" />
	</form>
	
<%} %>
</body>
</html>