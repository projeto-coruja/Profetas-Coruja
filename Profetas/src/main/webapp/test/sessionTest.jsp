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
	<c:when test="${empty sessionScope['userLoginSuccessfull'] || !sessionScope['userLoginSuccessfull']}">
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
		<br>
		<form method="post" action="/Profetas/doPasswordRecovery">
			<fieldset>				
				<!-- Login -->
				<label class="sidebar" for="login">Email:</label>
				<input class="inputLogin" type="text" name="mail" height="30px" size="auto" >
			</fieldset>
	
	
				<input class="buttonEntrar" type="submit" name="Entrar"	value="Entrar" />
		</form>
		<br><br><br>
		<form method="post" action="/Profetas/doRegister">
			<fieldset>			
				<label class="sidebar" for="login">Nome:</label>
				<input class="inputLogin" type="text" name="name" height="30px" size="auto" >
				<br>
				<label class="sidebar" for="login">Email:</label>
				<input class="inputLogin" type="text" name="mail" height="30px" size="auto" >
				<br>
				<label class="sidebar" for="senha">Senha:</label>
				<input class="inputLogin" type="password" name="password" height="30px" size="auto" >
				<br>
				<label class="sidebar" for="senha">Senha:</label>
				<input class="inputLogin" type="password" name="confPassword" height="30px" size="auto" >
			</fieldset>
	
	
				<input class="buttonEntrar" type="submit" name="Entrar"	value="Registrar" />
		</form>
	</c:when>
	<c:otherwise>
		olá ${sessionScope['userName']}!  (${sessionScope['userMail']})
		<form method="post" action="/Profetas/doLogout">
			<input class="buttonEntrar" type="submit" name="Logout"	value="logout" />
		</form>
		${sessionScope['userAccessToken']}
		<br/><br/>
		<form method="post" action="/Profetas/doChangesToAccount">
			<fieldset>			
				<input type="hidden" name="action" value="changeSelfAccountInformation"/>
				
				<label class="sidebar" for="login">Email:</label>
				<input class="inputLogin" type="text" name="newMail" height="30px" size="auto" >
				<br>
				<label class="sidebar" for="senha">Senha:</label>
				<input class="inputLogin" type="password" name="newPassword" height="30px" size="auto" >
				<br>
				<label class="sidebar" for="senha">Senha:</label>
				<input class="inputLogin" type="password" name="confPassword" height="30px" size="auto" >
			</fieldset>
	
	
				<input class="buttonEntrar" type="submit" name="Entrar"	value="Registrar" />
		</form>
		<br/><br/><br/>
		<c:forEach var="permission" items="${sessionScope['userPermissions']}">
			<c:if test="${permission == 'default'}">Show Default's Stuffs!<br/></c:if>
			<c:if test="${permission == 'admin'}">Show Admin's Stuffs!<br/></c:if>
		</c:forEach>
		
	</c:otherwise>
</c:choose>
</body>
</html>