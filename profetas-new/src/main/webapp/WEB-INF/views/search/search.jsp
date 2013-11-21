<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="<c:url value='/static/js/grid.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/search.js' />" type="text/javascript"></script>

<div class="content">
	<div id="tabs">
		<ul>
			<li><a href="#personagem"><span>Personagem</span></a></li>
			<li><a href="#fontes-obras"><span>Fontes/Obras</span></a></li>
		</ul>
  		<div id="personagem">
		    <div class="input-box"><input type="text" id="txtPersonagem" size="100" placeholder="Search..." /></div>		
			<br />
  			
  			<div class="left">
				<div id="searchPersonagem" class="button button-size">Search</div>
			</div>
		</div>
		<div id="fontes-obras">
			<div class="input-box"><input type="text" id="txtFonteObra" size="100" placeholder="Search..." /></div>		
			<br />
  			
  			<div class="left">
				<div id="searchFonteObra" class="button button-size">Search</div>
			</div>
		</div>
	</div>
	
	<br />
	<div id="container_grid"></div>
</div>