<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script src="<c:url value='/static/js/grid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/religiaoCrencas.js'/>" type="text/javascript"></script>

<div class="content">
	<span class="title"><spring:message code="ttl_religiao_crencas"/></span>
	<form:form id="religiaoCrencas" modelAttribute="religiao">
		<form:hidden id="id" path="id" />
		<div class="label-box"><label for="nome"><spring:message code="lbl_nome"/></label></div>
	    <div class="input-box"><form:input id="nome" path="nome" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="anoInicio"><spring:message code="lbl_ano_inicio"/></label></div>
	    <div class="input-box"><form:input id="anoInicio" path="anoInicio" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="anoFim"><spring:message code="lbl_ano_fim"/></label></div>
	    <div class="input-box"><form:input id="anoFim" path="anoFim" size="50" maxlength="50" /></div>		
		<br />
		
		<div class="label-box"><label for="descricao"><spring:message code="lbl_descricao"/></label></div>
	    <div class="input-box"><form:input id="descricao" path="descricao" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="left">
			<div id="saveForm" class="button button-size"><spring:message code="btn_register"/></div>
		</div>
	</form:form>
	<br />
	<div id="container_grid">
		<div class="loader_container"><img src="<c:url value="/static/images/ajax-loader.gif"/>" /></div>
	</div>
</div>