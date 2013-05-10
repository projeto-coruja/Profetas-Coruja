<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sec:authorize access="isAnonymous()">
	<div id="loginTable" align="center">
		<form name="f" method="POST" action="<c:url value='j_spring_security_check' />">
			<input type="hidden" name="spring-security-redirect" value="/public" />
			<table>
				<tr>
					<td><label class="sidebar" for="login">Login:</label> <input
						class="inputLogin" type="text" name="j_username" height="30px"
						size="auto" placeholder=""></td>
		
					<td><label class="sidebar" for="senha">Senha:</label> <input
						class="inputLogin" type="password" name="j_password" height="30px"
						size="auto" placeholder=""></td>
		
					<td><button type="submit">Entrar</button></td>
					<td><button type="button"
							name="Registrar" value="Registrar" onclick="window.location = '<c:url value = "/register"/>'">Registrar</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<div id="loggedBar" align="center">
		<table>
			<tr>
				<td>Bem vindo de volta, <sec:authentication property="principal.nickname" />!</td>
				<td><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></td>
			</tr>
		</table>
	</div>			
</sec:authorize>