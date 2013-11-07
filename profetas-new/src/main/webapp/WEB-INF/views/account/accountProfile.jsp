<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('ADMIN')">
<script src="<c:url value='/static/js/grid.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/common.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/accountProfile.js'/>" type="text/javascript"></script>

<div class="content">
	<span class="title"><spring:message code="ttl_update_profile_user"/></span>
	<form:form id="account" modelAttribute="user">
		<form:hidden id="id" path="id" />
		<div class="label-box"><label for="idProfile"><spring:message code="lbl_profile"/></label></div>
	    <div class="input-box">
	    	<select id="idProfile" name="idProfile">
	    	</select>
	    </div>		
		<br />
		<div class="left">
			<div id="saveUser" class="button button-size"><spring:message code="btn_register"/></div>
		</div>
	</form:form>
	
	<br />
	<div id="container_grid">
		<div class="loader_container"><img src="<c:url value="/static/images/ajax-loader.gif"/>" /></div>
	</div>
</div>
</sec:authorize>