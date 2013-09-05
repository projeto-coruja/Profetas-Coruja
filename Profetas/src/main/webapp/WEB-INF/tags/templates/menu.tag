<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib tagdir="/WEB-INF/tags/functions/" prefix="func" %>

<!-- Home -->
<div class="bordaBox">
	<div class="conteudo">
		<article class="menuLateral">
			<ul class="nav" id="menu">
				<li><a class="borderTop" class="noBorderTop" href="<c:url value="/jsp/index.jsp" />">Home</a></li>
			</ul>
		</article>
	</div>
</div>

<!-- Pesquisa -->
<div class="bordaBox">
	<div class="conteudo">
		<article class="menuLateral">
			<ul class="nav" id="menu">
				<li><a class="borderTop" href="<c:url value="/jsp/search.jsp" />">Pesquisar entrada</a></li>
			</ul>
		</article>
	</div>
</div>

<func:contains string="admin" array="${sessionScope['userPermissions']}">
	<!-- Cadastros -->
	<div class="bordaBox">
		<div class="conteudo">
			<article class="menuLateral">
				<div class="dropDown">
					<ul class="nav" id="menu">
						<li><a class="borderTop" href="#">Cadastrar entrada</a>
							<div>
								<ul>
									<li><a class="borderTop" href="<c:url value="/jsp/newEntry.jsp?item=1" />">Obras/Fontes</a></li>
									<li><a href="<c:url value="/jsp/newEntry.jsp?item=2" />">Personagem</a></li>
									<li><a href="<c:url value="/jsp/newEntry.jsp?item=3" />">Grupo/Movimentos</a></li>
									<li><a href="<c:url value="/jsp/newEntry.jsp?item=4" />">Religião/Crenças</a></li>
									<li><a href="<c:url value="/jsp/newEntry.jsp?item=5" />">Encontro</a></li>
									<li><a href="<c:url value="/jsp/newEntry.jsp?item=6" />">Correspondencia</a></li>
									<li><a href="<c:url value="/jsp/newEntry.jsp?item=7" />">Local</a></li>
								</ul>
							</div>
						</li>	
					</ul>
				</div>
			</article>
		</div>
	</div>
	<!-- Controle -->
	<div class="bordaBox">
		<div class="conteudo">
			<article class="menuLateral">
				<div class="dropDown">
					<ul class="nav" id="menu">
						<li><a class="borderTop" href="#">Painel de Controle</a>
							<div>
								<ul>
									<li><a class="borderTop" href="#">Cadastrar Usuário</a></li>
									<li><a href="#">Gerar Senha</a></li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
			</article>
		</div>
	</div>
</func:contains>
<!-- Outros -->
<div class="bordaBox">
	<div class="conteudo">
		<article class="menuLateral">
			<ul class="nav" id="menu">
				<li><a class="borderTop" href="<c:url value="/jsp/tutorial.jsp" />">Tutorial</a></li>
				<li><a href="<c:url value="/jsp/about.jsp" />">Sobre</a></li>
				<li><a href="<c:url value="/jsp/contact.jsp" />">Contato</a></li>
				<li><a href="<c:url value="/jsp/credits.jsp" />">Créditos</a></li>
			</ul>
		</article>
	</div>
</div>