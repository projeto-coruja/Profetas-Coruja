<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="login.jsp" flush="true" />

<!-- Home -->
<div class="bordaBox">
	<div class="conteudo">
		<article class="menuLateral">
			<ul class="nav" id="menu">
				<li><a class="borderTop" class="noBorderTop" href="<c:url value="/index.html" />">Home</a></li>
			</ul>
		</article>
	</div>
</div>

<!-- Pesquisa -->
<div class="bordaBox">
	<div class="conteudo">
		<article class="menuLateral">
			<ul class="nav" id="menu">
				<li><a class="borderTop" href="<c:url value="/search.html" />">Pesquisar entrada</a></li>
			</ul>
		</article>
	</div>
</div>

	<!-- Admin -->
	<!-- Cadastros -->
	<sec:authorize access="hasRole('SAVE')">
	<div class="bordaBox">
		<div class="conteudo">
			<article class="menuLateral">
				<div class="dropDown">
					<ul class="nav" id="menu">
						<li><a class="borderTop" href="#">Cadastrar entrada</a>
							<div>
								<ul>
									<li><a class="borderTop" href="<c:url value="/local.html" />">Local</a></li>
									<li><a href="<c:url value="/grupo-movimento.html" />">Grupo/Movimentos</a></li>
									<li><a href="<c:url value="/religiao-crencas.html" />">Religião/Crenças</a></li>
									<li><a href="<c:url value="/correspondencia.html" />">Correspondencia</a></li>
									<li><a href="<c:url value="/personagem.html" />">Personagem</a></li>
									<li><a  href="<c:url value="/fontes-obras.html" />">Obras/Fontes</a></li>
								</ul>
							</div>
						</li>	
					</ul>
				</div>
			</article>
		</div>
	</div>
	</sec:authorize>
	
	<!-- Controle -->
	<sec:authorize access="hasRole('ADMIN')">
	<div class="bordaBox">
		<div class="conteudo">
			<article class="menuLateral">
				<div class="dropDown">
					<ul class="nav" id="menu">
						<li><a class="borderTop" href="#">Painel de Controle</a>
							<div>
								<ul>
									<li><a class="borderTop" href="<c:url value="/account.html" />">Cadastrar Usuário</a></li>
									<!--li><a href="#">Gerar Senha</a></li-->
									<li><a href="<c:url value="/account-profile.html" />">Usuários</a></li>									
								</ul>
							</div>
						</li>
					</ul>
				</div>
			</article>
		</div>
	</div>
	</sec:authorize>
<!-- Outros -->
<div class="bordaBox">
	<div class="conteudo">
		<article class="menuLateral">
			<ul class="nav" id="menu">
				<li><a class="borderTop" href="<c:url value="/tutorial.html" />">Tutorial</a></li>
				<li><a href="<c:url value="/about.html" />">Sobre</a></li>
				<li><a href="<c:url value="/contact.html" />">Contato</a></li>
				<li><a href="<c:url value="/credits.html" />">Créditos</a></li>
			</ul>
		</article>
	</div>
</div>