<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src="<c:url value='/static/js/grid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/local.js'/>" type="text/javascript"></script>

<div class="content">
	<span class="title"><spring:message code="ttl_local"/></span>

	<div id="mapdiv" style="width:100%; height:300px"></div>
	
	<form:form id="local" modelAttribute="local">
		<form:hidden id="id" path="id" />
		<div class="label-box"><label for="nome"><spring:message code="lbl_nome"/></label></div>
	    <div class="input-box"><form:input id="nome" path="nome" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="country"><spring:message code="lbl_country"/></label></div>
	    <div class="input-box"><form:input id="country" path="country" size="50" maxlength="100" /></div>		
		<br />
		<div class="label-box"><label for="state"><spring:message code="lbl_state"/></label></div>
	    <div class="input-box"><form:input id="state" path="state" size="50" maxlength="100" /></div>		
		<br />
		<div class="label-box"><label for="city"><spring:message code="lbl_city"/></label></div>
	    <div class="input-box"><form:input id="city" path="city" size="50" maxlength="100" /></div>		
		<br />
		
	    <form:hidden id="latitude" path="latitude" />		
	    <form:hidden id="longitude" path="longitude" />	
		
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