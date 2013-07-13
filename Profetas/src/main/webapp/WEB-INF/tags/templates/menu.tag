<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!-- Home -->
<div class="bordaBox">
	<div class="conteudo">
		<article class="menuLateral">
			<ul class="nav" id="menu">
				<li><a class="borderTop" class="noBorderTop" href="index.jsp">Home</a></li>
			</ul>
		</article>
	</div>
</div>

<!-- Pesquisa -->
<div class="bordaBox">
	<div class="conteudo">
		<article class="menuLateral">
			<ul class="nav" id="menu">
				<li><a class="borderTop" href="pesquisa.jsp">Pesquisar Documento</a></li>
			</ul>
		</article>
	</div>
</div>
<c:forEach var="permission" items="${sessionScope['userPermissions']}">
	<c:if test="${permission == 'admin'}">
		<!-- Cadastros -->
		<div class="bordaBox">
			<div class="conteudo">
				<article class="menuLateral">
					<ul class="nav" id="menu">
						<li><a class="borderTop" href="cadastrarOrigem.jsp">Cadastrar Códice/Caixa</a></li>	
						<li><a href="cadastroDocumentos.jsp">Cadastrar Documento</a></li>
						<li><a href="cadastrarPalavrasChave.jsp">Cadastrar Palavra-Chave</a></li>
						<li><a href="cadastrarTipoDocumento.jsp">Cadastrar Tipo de Documento</a></li>
					</ul>
				</article>
			</div>
		</div>
		
		<!-- Controle -->
		<div class="bordaBox">
			<div class="conteudo">
				<article class="menuLateral">
					<ul class="nav" id="menu">
						<li><a class="borderTop" href="painel.jsp">Painel de Controle</a></li>
						<li><a href="cadUser.jsp">Cadastrar Usuário</a></li>
						<li><a href="gerarSenha.jsp">Gerar Senha</a></li>
					</ul>
				</article>
			</div>
		</div>
	</c:if>
</c:forEach>
<!-- Outros -->
<div class="bordaBox">
	<div class="conteudo">
		<article class="menuLateral">
			<ul class="nav" id="menu">
				<li><a class="borderTop" href="tutorialPesquisa.jsp">Como pesquisar no acervo</a></li>
				<li><a href="sobre.jsp">Sobre</a></li>
				<li><a href="contato.jsp">Contato</a></li>
				<li><a href="creditos.jsp">Créditos</a></li>
			</ul>
		</article>
	</div>
</div>