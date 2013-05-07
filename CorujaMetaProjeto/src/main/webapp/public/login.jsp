<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="snp" tagdir="/WEB-INF/templates/" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>Profetas - Login</title>
		
		<!-- Import dos styles CSS -->
		<link rel="stylesheet" type="text/css" href="<c:url value = "/assets/css/main.css"/>" />		
		
	</head>
	<body style="text-align:-moz-center;">
		<div style="border:1px solid; height:124px; width:1024px;">Banner</div>
		<div id="global">
			<div id="header">
				<c:if test="${not empty failure }">
					<div id="header" style="color:red; height:110px; padding-top:4px; width:408px; margin-left:320px;">
						<h1>Login incorreto!</h1>
						<br>Por favor tente novamente.
					</div>
				</c:if>
			</div>
			<div class="loginForm">
				<form action="<c:url value="j_spring_security_check" />" method="post">
					<div>
						Login:
					</div>
					<table>
						<tr>
							<td><label for="j_username">Email:</label> 
								<input type="text" name="j_username" height="30px"size="auto" placeholder=""></td>
						</tr>
						<tr>
							<td><label for="j_password">Senha:</label> 
								<input type="password" name="j_password" height="30px"size="auto" placeholder=""></td>
						</tr>
						<tr>
							<td></td>
							<td><a style="text-align:left; margin-right:10px;" href="public">Home</a><button type="submit">Login</button></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="footer">
				<snp:footer />
			</div>
		</div>
	</body>
</html>