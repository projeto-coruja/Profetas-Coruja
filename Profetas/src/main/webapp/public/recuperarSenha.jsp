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
				<h1>Recuperar Senha</h1>
				<form id="signupform" autocomplete="off" method="post" action="/GraoPara/passwordRecovery">
					<table class="tableForms">
						<tr>
							<td class="tdForms">
								<label class="labelForms" id="lmail" for="mail">Email</label>
							</td>
						</tr>
						
						<tr>
							<td class="field">
								<input class="input" name="email" id="email" type="text" size="20" maxlength="1024" />
							</td>
							<td class="status"></td>
						</tr>
						<tr>
							<td class="tdForms2">
								<br>
								<input class="buttonRegistrar" id="signupsubmit" name="inserir" type="submit" value="Enviar" />
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