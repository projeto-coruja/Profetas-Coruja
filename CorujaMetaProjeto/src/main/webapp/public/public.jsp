<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="snp" tagdir="/WEB-INF/templates/" %>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Profetas</title>

		<!-- Import dos styles CSS -->
		<link rel="stylesheet" type="text/css" href="<c:url value = "/assets/css/main.css"/>" />
		<link rel="stylesheet" type="text/css" href="<c:url value = "/assets/css/loginArea.css"/>" />
		<link rel="stylesheet" type="text/css" href="<c:url value = "/assets/css/floatingMenu.css"/>" />

	</head>
	<body>
		<div id="login"><snp:defaultLogin /></div>
		<div id="global">
			<div id="header"><snp:defaultMenu /></div>
			
			<div id="middle">
				<!-- Texto da Pagina inicial -->
				<snp:welcomeText />
			</div>
			
			<div id="footer">
				<snp:footer />
			</div>
		</div>
	</body>
</html>