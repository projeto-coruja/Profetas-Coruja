<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script src="<c:url value='/static/js/grid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/correspondencia.js'/>" type="text/javascript"></script>

<div class="content">
	<span class="title"><spring:message code="ttl_correspondencia"/></span>
	
	<form:form id="correspondencia" modelAttribute="correspondencia">
		<form:hidden id="id" path="id" />
		<div class="label-box"><label for="idRemetente"><spring:message code="lbl_remetente"/></label></div>
	    <div class="input-box">
	    	<select id="idRemetente" name="idRemetente">
				<option value="-1">Choose one</option>	    	
	    	</select>
	    </div>		
		<br />
		
		<div class="label-box"><label for="idDestinatario"><spring:message code="lbl_destinatario"/></label></div>
	    <div class="input-box">
	    	<select id="idDestinatario" name="idDestinatario">
				<option value="-1">Choose one</option>	    	
	    	</select>
	    </div>		
		<br />
		
		<div class="label-box"><label for="idLocal"><spring:message code="lbl_loc"/></label></div>
	    <div class="input-box">
	    	<select id="idLocal" name="idLocal">
				<option value="-1">Choose one</option>	    	
	    	</select>
	    </div>		
		<br />
		
		<div class="label-box"><label for="data"><spring:message code="lbl_data"/></label></div>
	    <div class="input-box"><form:input id="data" path="data" size="50" maxlength="100" /></div>		
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