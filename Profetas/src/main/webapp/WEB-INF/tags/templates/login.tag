<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<div class="bordaBox">
	<div class="conteudo">
		<div class="LoginArea" id="loginDefault" style="display:block;">
			<c:choose>
				<c:when test="${empty sessionScope['userLoginSuccessfull'] || !sessionScope['userLoginSuccessfull']}">
					<form method="post" action="<c:url value="/doLogin"/>">
						<fieldset>				
							<!-- Login -->
							<label class="sidebar" for="login">Login:</label>
							<input class="inputLogin" type="text" name="user" height="30px" size="auto" placeholder="">
		
							<!-- Senha -->
							<label class="sidebar" for="senha">Senha:</label>
							<input class="inputLogin" type="password" name="password" height="30px" size="auto" placeholder="">
						</fieldset>
						
						<!-- <div>
							<tr>
								<td><input class="buttonEntrar" type="submit" name="Entrar"	value="Entrar" /></td>
								<td><a href="/GraoPara/public/userCadastre.jsp"><input type="button" class="buttonRegistrar" name="Registrar" value="Registrar"></a></td>						
								<p></p>
							</tr>
						</div> -->
						
						<div style="text-align:center; padding:0px 1px 8px;">
							<input class="buttonEntrar" type="submit" name="Entrar"	value="Entrar" />
							<a style="text-decoration: none; "href="<c:url value="/public/userCadastre.jsp"/>"><input type="button" class="buttonRegistrar" name="Registrar" value="Registrar"></a>
						</div>
						<div style="text-align:center; margin:-5px 0px -15px;">
							<a style="text-decoration: none; "href="<c:url value="/public/recuperarSenha.jsp"/>">Esqueci a senha</a>
						</div>
					</form>
				</c:when>
				
				<c:otherwise>
					<form method="post" action="<c:url value="/doLogout"/>">
						<fieldset>
							<label class="sidebar" for="login">
								Bem vindo ${sessionScope['userName']}
							</label>
							
						</fieldset>
		
						<fieldset style="padding-left:17px">
							<input class="buttonSair" type="submit" name="Sair" value="Sair" />
						</fieldset>
					</form>
				</c:otherwise>
				
			</c:choose>
		</div>
	</div>
</div>