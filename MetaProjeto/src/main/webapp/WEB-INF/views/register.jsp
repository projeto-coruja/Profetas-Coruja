<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="snp" tagdir="/WEB-INF/tags/snippets/" %>
<%@ taglib prefix="own" uri="/WEB-INF/tags/coruja-taglib.tld" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Profetas - Registrar</title>
		
		<!-- Import dos styles CSS -->
		<link rel="stylesheet" type="text/css" href="<c:url value = "/assets/css/main.css"/>" />		
		
	</head>
	<body>
		<div id="global">
			<div id="header">
				<c:choose>
					<c:when test="${not empty success}">
						<h1><c:out value="${success}" /></h1>
					</c:when>
					<c:when test="${not empty failure }">
						<h1><c:out value="${failure}" /></h1>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<h2>Cadastro de usuários:</h2>
			</div>
			<div id="middle">
				<form:form action="createAccount" method="post" modelAttribute="newAccount">
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
							<td><form:button type="submit">Registrar</form:button></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="footer">
				<snp:footer />
			</div>
		</div>
	</body>
</html>