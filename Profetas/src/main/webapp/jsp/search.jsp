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
				<h1>Pesquisa de Documento 
					<label style="font-size: x-small; color: black; padding-left: 10px;">(Os campos são opcionais.)</label>
				</h1>
				<div class="collapsibleMenu">
					<a class="collapse" href="#">Pesquisa Básica</a>
					<div class="collapsible">
						<form action="<c:url value="/jsp/yay.jsp"/>" id="signupform" method="get" name="cadastro">
							<table class="collapsibleTableForms">
								<!-- 
								<tr>
									<td class="tdPesquisa"><label class="labelFormsSearch" id="lautorOcupacao" for="autorOcupacao">&#8226 Ocupação do Autor do Documento</label></td>
								<tr>	
								-->
								<tr>
									<td class="field"><input class="input" name="autorOcupacao" id="autorOcupacao" type="text" size="20" maxlength="1024">
									<label></label><a href="#"><img class="iconeAjuda" src="<c:url value="/images/icone_ajuda.png"/>" title="Ocupação do autor do documento. Por exemplo, Presidente da Província." /></a>
									</td>
								</tr>
								
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
					<br>
					<a class="collapse" href="#">Pesquisa Avançada</a>
					<div class="collapsible">
						<form action="<c:url value="/jsp/yay.jsp"/>" id="signupform" method="get" name="cadastro">
							<table class="collapsibleTableForms">
								<tr>
									<td class="tdPesquisa"><label class="labelFormsSearch" id="ltitulo" for="titulo">&#8226 Título</label></td>
								<tr>	
								<tr>
									<td class="field"><input class="input" name="titulo" id="titulo" type="text" size="20" maxlength="1024">
									<label></label><a href="#"><img class="iconeAjuda" src="<c:url value="/images/icone_ajuda.png"/>" title="Ocupação do autor do documento. Por exemplo, Presidente da Província." /></a>
									</td>
								</tr>
								<tr>
									<td class="tdPesquisa"><label class="labelFormsSearch" id="ltitulo" for="titulo">&#8226 Título</label></td>
								<tr>	
								<tr>
									<td class="field"><input class="input" name="titulo" id="titulo" type="text" size="20" maxlength="1024">
									<label></label><a href="#"><img class="iconeAjuda" src="<c:url value="/images/icone_ajuda.png"/>" title="Ocupação do autor do documento. Por exemplo, Presidente da Província." /></a>
									</td>
								</tr>
								<tr>
									<td class="tdPesquisa"><label class="labelFormsSearch" id="ltitulo" for="titulo">&#8226 Título</label></td>
								<tr>	
								<tr>
									<td class="field"><input class="input" name="titulo" id="titulo" type="text" size="20" maxlength="1024">
									<label></label><a href="#"><img class="iconeAjuda" src="<c:url value="/images/icone_ajuda.png"/>" title="Ocupação do autor do documento. Por exemplo, Presidente da Província." /></a>
									</td>
								</tr>
								<tr>
									<td class="tdPesquisa"><label class="labelFormsSearch" id="ltitulo" for="titulo">&#8226 Título</label></td>
								<tr>	
								<tr>
									<td class="field"><input class="input" name="titulo" id="titulo" type="text" size="20" maxlength="1024">
									<label></label><a href="#"><img class="iconeAjuda" src="<c:url value="/images/icone_ajuda.png"/>" title="Ocupação do autor do documento. Por exemplo, Presidente da Província." /></a>
									</td>
								</tr>
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