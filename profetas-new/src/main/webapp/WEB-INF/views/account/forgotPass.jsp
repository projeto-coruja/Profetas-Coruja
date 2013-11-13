<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAnonymous()">

<script src="<c:url value='/static/js/common.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/forgotPass.js'/>" type="text/javascript"></script>

<div class="content">
	<span class="title"><spring:message code="ttl_forgot_pass"/></span>
	<form:form id="forgotPass" modelAttribute="user">
		<div class="label-box"><label for="email"><spring:message code="lbl_email"/></label></div>
	    <div class="input-box"><form:input id="email" path="email" size="50" maxlength="100" /></div>		
		<br />

		<div class="left">
			<div id="saveForm" class="button button-size"><spring:message code="btn_submit"/></div>
		</div>
	</form:form>
</div>

</sec:authorize>