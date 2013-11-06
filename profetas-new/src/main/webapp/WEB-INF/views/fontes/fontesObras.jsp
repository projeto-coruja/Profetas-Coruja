<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script src="<c:url value='/static/js/grid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/fontesObras.js'/>" type="text/javascript"></script>

<div class="content">
	<span class="title"><spring:message code="ttl_obras_fontes"/></span>
	
	<form:form id="fontes" modelAttribute="fontes">
		<form:hidden id="id" path="id" />
		<div class="label-box"><label for="titulo"><spring:message code="lbl_titulo"/></label></div>
	    <div class="input-box"><form:input id="titulo" path="titulo" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for=referenciasCirculacaoObra><spring:message code="lbl_referencias"/></label></div>
	    <div class="input-box"><form:input id="referenciasCirculacaoObra" path="referenciasCirculacaoObra" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="comentarios"><spring:message code="lbl_comentarios"/></label></div>
	    <div class="input-box"><form:input id="comentarios" path="comentarios" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="url"><spring:message code="lbl_url"/></label></div>
	    <div class="input-box"><form:input id="url" path="url" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="copiasManuscritas"><spring:message code="lbl_copias"/></label></div>
	    <div class="input-box"><form:input id="copiasManuscritas" path="copiasManuscritas" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="traducoes"><spring:message code="lbl_traducoes"/></label></div>
	    <div class="input-box"><form:input id="traducoes" path="traducoes" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="editor"><spring:message code="lbl_editor"/></label></div>
	    <div class="input-box"><form:input id="editor" path="editor" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="dataImpressao"><spring:message code="lbl_data_impressao"/></label></div>
	    <div class="input-box"><form:input id="dataImpressao" path="dataImpressao" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="idLocalImpressao"><spring:message code="lbl_local_impressao"/></label></div>
	    <div class="input-box">
	    	<select id="idLocalImpressao" name="idLocalImpressao">
				<option value="-1">Choose one</option>	    	
	    	</select>
	    </div>		
		<br />
		
		<div class="label-box"><label for="idClassificacao"><spring:message code="lbl_classificacao"/></label></div>
	    <div class="input-box">
	    	<select id="idClassificacao" name="idClassificacao">
				<option value="-1">Choose one</option>	    	
	    	</select>
	    </div>		
		<br />
		
		<div class="left">
			<div id="saveForm" class="button button-size"><spring:message code="btn_register"/></div>
		</div>
	</form:form>
	<div>
</div>
	
	<br />
	<div id="container_grid">
		<div class="loader_container"><img src="<c:url value="/static/images/ajax-loader.gif"/>" /></div>
	</div>
</div>