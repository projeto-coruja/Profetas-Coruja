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
				<form action="/GraoPara/public/listagemDocumentos.jsp" id="signupform" method="get" name="cadastro">
					<snp:searchForm/>
				</form>
			</div>
			
			<!-- Rodape -->
			<snp:footer />
		</div>
	</body>
</html>