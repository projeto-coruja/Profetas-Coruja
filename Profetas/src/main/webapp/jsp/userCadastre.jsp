<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="snp" tagdir="/WEB-INF/tags/templates/"%>

<html>
	<head>
		<snp:header type="full" />
	</head>

	<body>
		<div class="container">
			<div class="header"></div>

			<!-- Menu lateral -->
			<div class="sidebar1">
				<!-- Area de login -->
				<snp:login />

				<!-- Area de menu -->
				<snp:menu />
			</div>

			<div class="content" id="content">
				<h1>Cadastro de Usuário</h1>

				<form id="signupform" autocomplete="off" method="post" action="<c:url value ="/doRegister"/>">
					<table class="tableForms">
					
						<!-- Nome -->
						<tr>
							<td class="tdForms2">
								<label id="lfirstname" class="labelForms" for="name">Nome</label>
							</td>
						</tr>
						
						<tr>
							<td class="field">
								<input id="nome" class="input" type="text" name="name" maxlength="100" style="width:350px;"/>
							</td>
							<td class="status"></td>
						</tr>
					
						<!-- Email -->
						<tr>
							<td class="tdForms2">
								<label id="lemail" class="labelForms"  for="mail" >Email</label>
							</td>
						</tr>
						
						<tr>
							<td class="field"><input id="email" class="input" type="text" name="mail" maxlength="150" style="width:350px;"/></td>
							<td class="status"></td>
						</tr>
					
						<!-- Senha -->
						<tr>
							<td class="tdForms2">
								<label id="lpassword" class="labelForms" for="password">Senha</label>
							</td>
						</tr>
						
						<tr>
							<td class="field">
								<input id="password" class="inputPass" type="password" name="password" maxlength="50"/>
							</td>
							<td class="status"></td>
						</tr>
					
						<!-- Confirma Senha -->
						<tr>
							<td class="tdForms2">
								<label id="lpassword_confirm" class="labelForms" for="confPassword">Confirma Senha</label>
							</td>
						</tr>
						
						<tr>
							<td class="field">
								<input id="password_confirm" class="inputPass" type="password" name="confPassword" maxlength="50"/>
							</td>
							<td class="status"></td>
						</tr>
					
						<!-- Botao Cadastrar -->
						<tr>
							<td class="tdForms2">
								<br>
								<input id="signupsubmit" class="buttonRegistrar" type="submit" name="inserir" value="Cadastrar"/>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<!-- Rodape -->
			<snp:footer />
		</div>
	</body>
</html>