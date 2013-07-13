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
				<snp:menu />
			</div>

			<!-- Conteudo -->
			<table class="text">
				<tr>
					<td>
						<h2>Sobre o projeto Documentos do Grão-Pará: 1800-1850</h2>
			
						<p>
						Mussum ipsum cacilds, vidis litro abertis. Consetis adipiscings
						elitis. Pra lá , depois divoltis porris, paradis. Paisis, filhis,
						espiritis santis. Mé faiz elementum girarzis, nisi eros vermeio,
						in elementis mé pra quem é amistosis quis leo. Manduma pindureta
						quium dia nois paga. Sapien in monti palavris qui num significa
						nadis i pareci latim. Interessantiss quisso pudia ce receita de
						bolis, mais bolis eu num gostis. <br /> Suco de cevadiss, é um
						leite divinis, qui tem lupuliz, matis, aguis e fermentis. Interagi
						no mé, cursus quis, vehicula ac nisi. Aenean vel dui dui. Nullam
						leo erat, aliquet quis tempus a, posuere ut mi. Ut scelerisque
						neque et turpis posuere pulvinar pellentesque nibh ullamcorper.
						Pharetra in mattis molestie, volutpat elementum justo. Aenean ut
						ante turpis. Pellentesque laoreet mé vel lectus scelerisque
						interdum cursus velit auctor. Lorem ipsum dolor sit amet,
						consectetur adipiscing elit. Etiam ac mauris lectus, non
						scelerisque augue. Aenean justo massa.
						</p>
					</td>
				</tr>
			</table>

			<!-- Rodape -->
			<snp:footer />
		</div>
	</body>
</html>