<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="snp" tagdir="/WEB-INF/templates/" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>Profetas - Registrar</title>
		
		<!-- Import dos styles CSS -->
		<link rel="stylesheet" type="text/css" href="<c:url value = "/assets/css/main.css"/>" />		
		
	</head>
	<body style="text-align:-moz-center;">
		<div style="border:1px solid; height:124px; width:1024px;">Banner</div>
		<div id="global">
			<div id="header">
				<c:choose>
					<c:when test="${not empty success}">
						<script>
							alert("${success}");
							document.location="<c:url value=\"public\" />";
						</script>
					</c:when>
					<c:when test="${not empty failure }">
						<div id="header" style="color:red; height:110px; padding-top:4px; width:408px; margin-left:320px;">
						<h1><c:out value="${failure}" /></h1>
						<c:forEach items="${form_errors}" var="error"> 
							<c:out value="${error }" />
						</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="registerForm">
				<form action="/createAccount" method="post" >
					<div>
						Cadastro de usuários:
					</div>
					<table>
						<tr>
							<td><form:label path="nickname">Apelido:</form:label></td>
							<td><form:input path="nickname" /></td>
						</tr>
						<tr>
							<td><form:label path="username">E-mail:</form:label></td>
							<td><form:input path="username" type="email"/></td>
						</tr>
						<tr>
							<td><form:label path="password">Senha:</form:label></td>
							<td><form:password path="password"/></td>
						</tr>
						<tr>
							<td><form:label path="repeatPassword">Repita a senha:</form:label></td>
							<td><form:password path="repeatPassword"/></td>
						</tr>
						<tr>
							<td></td>
							<td><a style="text-align:left; margin-right:10px;" href="public">Home</a><form:button type="submit">Registrar</form:button></td>
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