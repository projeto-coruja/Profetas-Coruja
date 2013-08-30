<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="snp" tagdir="/WEB-INF/tags/templates/"%>
<%@ taglib prefix="func" tagdir="/WEB-INF/tags/functions/"%>

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
						<func:field name="name" tip="" title="Nome" hasStatus="true">
							<input id="nome" class="input" type="text" name="name" maxlength="100" style="width:350px;"/>
						</func:field>
						
						<!-- Email -->
						<func:field name="mail" tip="" title="Email" hasStatus="true">
							<input id="mail" class="input" type="text" name="mail" maxlength="100" style="width:350px;"/>
						</func:field>
						
						<!-- Senha -->
						<func:field name="password" tip="" title="Senha" hasStatus="true">
							<input id="password" class="input" type="text" name="password" maxlength="100" style="width:350px;"/>
						</func:field>
						
						<!-- Confirma Senha -->
						<func:field name="password_confirm" tip="" title="Confirma Senha" hasStatus="true">
							<input id="password_confirm" class="input" type="text" name="password_confirm" maxlength="100" style="width:350px;"/>
						</func:field>
						
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