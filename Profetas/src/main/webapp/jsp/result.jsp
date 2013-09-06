<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="snp" tagdir="/WEB-INF/tags/templates/"%>
<%@ taglib prefix="func" tagdir="/WEB-INF/tags/functions/"%>

<html>
	<head>
		<snp:header type="full">
			<style type="text/css">
				.h1form {
					float: left;
					margin-right: -337px;
					padding: 0;
				}
				.tdList{
					padding: 5px 0px 5px 10px;
					border: none;
				}
				#resultTools{
					padding: 6px 102px 0 0;
					text-align: right;
				}
			</style>
		</snp:header>
	</head>

	<body onloadstart="checkCookie()">
		<div class="container">
			<div class="header"></div>

			<!-- Menu lateral -->
			<div class="sidebar1">

				<!-- Area de menu -->
				<snp:login/>
				
				<!-- Area de menu -->
				<snp:menu />
			</div>

			<div class="content" id="content">
				<h1 class="h1form">Resultado</h1>

				<div id="resultTools" align="right">
					<a class="tdMinilink" href="javascript:confirmBox('<c:url value="jsp/yay.jsp"/>')">Download Pesquisa</a>
					<label style="font-size: small">|</label>
					<a class="tdMinilink" href="http://pt-br.libreoffice.org/baixe-ja/" target="_blank" title="Os documentos são baixados no formato ODT e podem ser visualizados utilizando LibreOffice. Clique aqui para baixar o LibreOffice!">Baixar o LibreOffice</a>
				</div>
				<table class="tableResultList">
					<c:forEach var="item" items="${result}">
						<c:choose>
							<c:when test="${resultType == 'character' }">
								<tr class="trList">
									<td class="tdList">
										Nome: ${item.nome} (${item.apelido})<br/>
										Formação: ${item.formacao}<br/>
										Ocupação: ${item.ocupacao}<br/>
										Local de nascimento: ${item.localNascimento.nome} - ${item.dataNascimento.day}/${item.dataNascimento.month}/${item.dataNascimento.year}<br/>
										Local de morte: ${item.localMorte.nome} - ${item.dataMorte.day}/${item.dataMorte.month}/${item.dataMorte.year}
									</td>
									<td class="tdList">
										<a href="#">Bibliografia</a><br/>
										<a href="#">Grupo(s)</a><br/>
										<a href="#">Locais Visitado</a>
									</td>
								</tr>
							</c:when>
							<c:when test="${resultType == 'bibliography' }">
								<tr class="trList">
									<td class="tdList">
										Nome: ${item.titulo}<br/>
										Editor: ${item.editor}<br/>
										Data de impressão: ${item.dataImpressao.day}/${item.dataImpressao.month}/${item.dataImpressao.year}<br/>
										Classificação: ${item.classificao.tipo}<br/>
										Comentários: ${item.comentarios}<br/>
										URL: <a target="_blank" href="${item.URL}">http://UmLinkAssociadoAObra.com.br/</a>
									</td>
									<td class="tdList">
										<a href="#">Bibliografia</a><br/>
										<a href="#">Grupo(s)</a><br/>
										<a href="#">Locais Visitado</a>
									</td>
								</tr>
							</c:when>
						</c:choose>
					</c:forEach>
					<tr class="trList">
						<td colspan="2" style="text-align: center;">
							<a style="text-decoration: none;" href="#">Topo</a>
						</td>
					</tr>
				</table>
			</div>
			<!-- Rodape -->
			<snp:footer/>
		</div>
	</body>
</html>