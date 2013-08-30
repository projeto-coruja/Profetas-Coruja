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
				<h1 class="h1form">Pesquisa de Documento 
					<label style="font-size: x-small; color: black; padding-left: 10px;">(Os campos são opcionais.)</label>
				</h1>
				<div class="tabbedMenu">
					<ul>
						<li><a class="tabItem" href="#tab1">Pesquisa Básica</a></li>
						<li><a class="tabItem" href="#tab2">Pesquisa Avançada</a></li>
					</ul>
					<br>
					<div class="collapsible" id="tab1">
						<form action="<c:url value="/jsp/yay.jsp"/>" id="signupform" method="get" name="cadastro">
							<input type="hidden" name="type" value="basic">
							<table class="collapsibleTableForms">
							
								<func:field name="generalSearch" tip="Pesquisa geral" title="">
									<input class="input" name="generalSearch" id="generalSearch" type="text" size="20" maxlength="1024">
								</func:field>
								
								<tr>
									<td>
										<br>
										<input class="buttonRegistrar" name="inserir" type="submit" value="Pesquisar" id="signupsubmit" />
										<label style="padding-left: 10px;"></label>
										<input class="buttonLimpar" name="limpar" type="reset" value="Limpar" title="Limpar todos os campos preenchidos."/>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div class="collapsible" id="tab2">
						<form action="<c:url value="/jsp/yay.jsp"/>" id="signupform" method="get" name="cadastro">
							<input type="hidden" name="type" value="adv">
							<table class="collapsibleTableForms">
								<func:field name="nome" tip="Instrução do campo" title="Título do campo">
									<input class="input" name="nome" id="nome" type="text" size="20" maxlength="1024">
								</func:field>
								
								<func:field name="nome" tip="Instrução do campo" title="Título do campo de multiplas seleção">
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
								<func:field name="data" tip="Data" title="Título do campo de data">
									<snp:date/>
								</func:field>
								<tr>
									<td>
										<br>
										<input class="buttonRegistrar" name="inserir" type="submit" value="Pesquisar" id="signupsubmit" />
										<label style="padding-left: 10px;"></label>
										<input class="buttonLimpar" name="limpar" type="reset" value="Limpar" title="Limpar todos os campos preenchidos."/>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
			
			<!-- Rodape -->
			<snp:footer />
		</div>
	</body>
</html>