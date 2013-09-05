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
				<h1>Recuperar Senha</h1>
				<form id="signupform" autocomplete="off" method="post" action="<c:url value="/doPasswordRecovery"/>">
					<table class="tableForms">
					
						<func:field name="mail" tip="" title="Email">
							<input id="mail" class="input" type="text" name="mail" maxlength="100" style="width:350px;"/>
						</func:field>
						
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