<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" action="/GraoPara/doLogin">
	<table id="loginTable">
		<tr>
			<td><label class="sidebar" for="login">Login:</label> <input
				class="inputLogin" type="text" name="login" height="30px"
				size="auto" placeholder=""></td>

			<td><label class="sidebar" for="senha">Senha:</label> <input
				class="inputLogin" type="password" name="senha" height="30px"
				size="auto" placeholder=""></td>

			<td><button type="submit">Entrar</button></td>
			<td><button type="button"
					name="Registrar" value="Registrar" onclick="window.location = '<c:url value = "/register"/>'">Registrar</button>
			</td>
		</tr>
	</table>
</form>
