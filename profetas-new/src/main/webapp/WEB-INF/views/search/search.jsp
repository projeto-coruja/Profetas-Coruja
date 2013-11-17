<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="<c:url value='/static/js/search.js' />" type="text/javascript"></script>

<div class="content">
	<div id="tabs">
		<ul>
			<li><a href="#personagem"><span>Personagem</span></a></li>
			<li><a href="#fontes-obras"><span>Fontes/Obras</span></a></li>
		</ul>
  		<div id="personagem">
  			Pesquisa 1
		</div>
		<div id="fontes-obras">
			Pesquisa 2
		</div>
	</div>
</div>