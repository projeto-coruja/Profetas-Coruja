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
				<h1 class="h1form">
				<c:choose>
					<c:when test="${param.item == 1}">
						Cadastro de Obras/Fontes
					</c:when>
					<c:when test="${param.item == 2}">
						Cadastro de Personagem
					</c:when>
					<c:when test="${param.item == 3}">
						Cadastro de Grupo/Movimentos
					</c:when>
					<c:when test="${param.item == 4}">
						Cadastro de Religião/Crenças
					</c:when>
					<c:when test="${param.item == 5}">
						Cadastro de Encontro
					</c:when>
					<c:when test="${param.item == 6}">
						Cadastro de Correspondencia
					</c:when>
					<c:otherwise>
						Cadastro de Local
					</c:otherwise>
				</c:choose>
					<label style="font-size: x-small; color: black; padding-left: 10px;">(<font color="red">*</font> = Campo obrigatório)</label>
				</h1>
				<form action="<c:url value="/jsp/yay.jsp"/>" id="signupform" method="get" name="cadastro">
					<table class="tableForms">
						
						<func:field required="true" name="nome" tip="Instrução do campo" title="Título do campo">
							<input class="input" name="nome" id="nome" type="text" size="20" maxlength="1024">
						</func:field>
						
						<func:field required="false" name="nome" tip="Instrução do campo" title="Título do campo de multiplas seleção">
							<select name="nome[]" size="7" multiple style="width: 300px;">
								<option value="value">Item disponível para seleção</option>
								<option value="value">Item disponível para seleção</option>
								<option value="value">Item disponível para seleção</option>
								<option value="value">Item disponível para seleção</option>
								<option value="value">Item disponível para seleção</option>
								<option value="value">Item disponível para seleção</option>
								<option value="value">Item disponível para seleção</option>
								<option value="value">Item disponível para seleção</option>
								<option value="value">Item disponível para seleção</option>
								<option value="value">Item disponível para seleção</option>
							</select>
						</func:field>
						<func:field required="false" name="data" tip="Data" title="Título do campo de data">
							<snp:date/>
						</func:field>
						
						<tr>
							<td>
								<br>
								<input class="buttonRegistrar" name="inserir" type="submit" value="Cadastrar" id="signupsubmit" />
								<label style="padding-left: 10px;"></label>
								<input class="buttonLimpar" name="limpar" type="reset" value="Limpar" title="Limpar todos os campos preenchidos."/>
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