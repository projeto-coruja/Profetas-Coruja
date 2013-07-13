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
					<snp:newUserForm />
				</form>
			</div>

			<!-- Rodape -->
			<snp:footer />
		</div>
	</body>
</html>