<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Profetas</title>

		<!-- Import dos styles CSS -->
		<link rel="stylesheet" type="text/css" href="<c:url value = "/assets/css/public.css"/>" />

	</head>
	<body>
		<div id="login"><c:import url="/assets/templates/defaultLogin.jsp" /></div>
		<div id="global">
			<div id="header"><c:import url="/assets/templates/defaultMenu.jsp" /></div>
			
			<div id="middle">
				<!-- Texto da Pagina inicial -->
				<c:import url="/assets/templates/welcomeText.jsp" />
			</div>
			
			<div id="footer">
				<c:import url="/assets/templates/footer.jsp" />
			</div>
		</div>
	</body>
</html>