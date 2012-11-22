<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value = "/assets/css/public.css"/>" />
</head>
<body>
	<div id="global">
		<div id="header">
			<ul id="mainMenu">
				<li><a href="#">Home</a></li>
				<li><a href="#">Sobre</a></li>
				<li><a href="#">Busca</a>
					<ul>
						<li><a href="#">Vida</a></li>
						<li><a href="#">Universo</a></li>
					</ul>
				</li>
				<li><a href="#">Contato</a>
					<ul>
						<li><a href="#">Email</a></li>
						<li><a href="#">Sinal de fumaça</a></li>
					</ul>
				</li>
				<li><a href="#">Doe</a>
					<ul>
						<li><a href="#">Dinheiro</a></li>
						<li><a href="#">Coisas</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div id="middle">
			<a href="<c:url value="/home" />"> Do you exist?</a>
		</div>
	</div>
</body>
</html>