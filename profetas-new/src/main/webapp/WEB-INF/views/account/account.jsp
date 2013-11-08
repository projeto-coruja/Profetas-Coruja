<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!--sec:authorize access="hasRole('ADMIN')"-->
<script src="<c:url value='/static/js/account.js'/>" type="text/javascript"></script>
<script src="<c:url value='/static/js/common.js'/>" type="text/javascript"></script>

<div class="content">
	<span class="title"><spring:message code="ttl_register_user"/></span>
	<form:form id="account" modelAttribute="user">
		<form:hidden id="id" path="id" />
		<div class="label-box"><label for="fullname"><spring:message code="lbl_nome"/></label></div>
	    <div class="input-box"><form:input id="fullName" path="fullName" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="email"><spring:message code="lbl_email"/></label></div>
	    <div class="input-box"><form:input id="email" path="email" size="50" maxlength="100" /></div>		
		<br />
		
		<div class="label-box"><label for="password"><spring:message code="lbl_password"/></label></div>
	    <div class="input-box"><form:password id="passwordOrig" path="password" size="50" maxlength="50" /></div>		
		<br />
		
		<div class="label-box"><label for="passwordCopy"><spring:message code="lbl_confirm_pass"/></label></div>
	    <div class="input-box"><form:password id="passwordCopy" path="passwordCopy" size="50" maxlength="100" />	</div>		
		<br />
		
		<div class="left">
			<div id="saveUser" class="button button-size"><spring:message code="btn_register"/></div>
		</div>
	</form:form>
</div>
<!--/sec:authorize-->