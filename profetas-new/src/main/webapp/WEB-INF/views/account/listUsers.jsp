<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('ADMIN')">
	<script src="<c:url value='/static/js/grid.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/static/js/common.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/static/js/listUsers.js'/>" type="text/javascript"></script>
	
	<div class="content">
		<span class="title"><spring:message code="ttl_list_users"/></span>
		
		<br />
		<div id="container_grid">
			<div class="loader_container"><img src="<c:url value="/static/images/ajax-loader.gif"/>" /></div>
		</div>
	</div>
</sec:authorize>