<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script src="<c:url value='/static/js/grid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/personagem.js'/>" type="text/javascript"></script>

<div class="content">
	<span class="title"><spring:message code="ttl_personagem"/></span>
	
	<form:form id="personagem" modelAttribute="personagem">
		<form:hidden id="id" path="id" />
		<div class="label-box"><label for="nome"><spring:message code="lbl_nome"/></label></div>
	    <div class="input-box"><form:input id="nome" path="nome" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="apelido"><spring:message code="lbl_apelido"/></label></div>
	    <div class="input-box"><form:input id="apelido" path="apelido" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="loc_nascimento"><spring:message code="lbl_loc_nascimento"/></label></div>
	    <div class="input-box">
	    	<select id="idNascimento" name="idNascimento">
				<option value="-1">Choose one</option>	    	
	    	</select>
	    </div>		
		<br />
		
		<div class="label-box"><label for="data_nascimento"><spring:message code="lbl_data_nascimento"/></label></div>
	    <div class="input-box"><form:input id="dataNascimento" path="dataNascimento" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="loc_morte"><spring:message code="lbl_loc_morte"/></label></div>
	    <div class="input-box">
	    	<select id="idMorte" name="idMorte">
				<option value="-1">Choose one</option>	    	
	    	</select>
	    </div>		
		<br />
		
		<div class="label-box"><label for="data_morte"><spring:message code="lbl_data_morte"/></label></div>
	    <div class="input-box"><form:input id="dataMorte" path="dataMorte" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="biografia"><spring:message code="lbl_biografia"/></label></div>
	    <div class="input-box"><form:input id="biografia" path="biografia" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="ocupacao"><spring:message code="lbl_ocupacao"/></label></div>
	    <div class="input-box"><form:input id="ocupacao" path="ocupacao" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="formacao"><spring:message code="lbl_formacao"/></label></div>
	    <div class="input-box"><form:input id="formacao" path="formacao" size="50" maxlength="100" /></div>		
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