<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script src="<c:url value='/static/js/registerUser.js'/>" type="text/javascript"></script>

<div class="content">
	<sec:authorize access="isAnonymous()">
		<span class="title"><spring:message code="ttl_register_user"/></span>
		<form:form id="account" modelAttribute="user">
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
	</sec:authorize>
	<sec:authorize access="!isAnonymous()">
		<spring:message code="page_forbidden"/>
	</sec:authorize>				
</div>