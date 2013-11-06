<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script src="<c:url value='/static/js/grid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/common.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/profile.js'/>" type="text/javascript"></script>

<div class="content">
	<span class="title"><spring:message code="ttl_profile"/></span>
	<form:form id="profile" modelAttribute="profile">
		<form:hidden id="id" path="id" />
		<div class="label-box"><label for="name"><spring:message code="lbl_nome"/></label></div>
	    <div class="input-box"><form:input id="name" path="name" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="idRoles"><spring:message code="lbl_roles"/></label></div>
	    <div class="input-box">
	    	<select id="idRoles" name="idRoles" multiple>
	    	</select>
	    </div>		
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