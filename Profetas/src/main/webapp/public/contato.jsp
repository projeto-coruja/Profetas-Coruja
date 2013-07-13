<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="snp" tagdir="/WEB-INF/tags/templates/"%>

<html>
	<head>
		<snp:header type="basic" />
	</head>

	<body>
		<div class="container">
			<div class="header"></div>

			<!-- Menu lateral -->
			<div class="sidebar1">
			
				<!-- Area de login -->
				<snp:login />

				<!-- Area de menu -->
				<snp:login />
			</div>

			<!-- conteúdo -->
			<table class="text">
				<tr>
					<td>
						<h2>          Contato</h2>
						
						Prof. Dr. André Roberto de A. Machado<br />
						Coordenador Geral do Projeto <br />
						<br />
						Endereço:<br />
						Departamento de História da Universidade Federal de São Paulo – UNIFESP<br />
						Estrada do Caminho Velho, 333<br />
						Guarulhos, São Paulo <br />
						CEP 07252-312<br />
						<br />
						E-mail:<br />
						<a href="mailto:xxx@unifesp.br">andre.machado@unifesp.br</a><br />
			
					</td>
				</tr>
			</table>

			<!-- Rodape -->
			<snp:footer />
		</div>
	</body>
</html>