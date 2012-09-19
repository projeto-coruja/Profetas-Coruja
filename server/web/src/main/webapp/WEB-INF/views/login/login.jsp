<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="login-form" class="wrapper-form">
	<div id="form-title" class="form-header"><spring:message code="login_maritaca"/></div>
	<jsp:include page="../commons/messages.jsp" flush="true"/>
	<div id="form-body">
		<c:url var="url" value="/login.html" />
   		<form:form id="login" modelAttribute="user" method="POST" action="${url}">
			<div class="wrapper-row">
				<div class="wrapper-label"><label for="email"><spring:message code="account_email"/>:</label></div>
				<div class="wrapper-widget">
					<form:input id="email" path="email" maxlength="50" />
				</div>
			</div>
			<div class="wrapper-row">
				<div class="wrapper-label"><label for="password"><spring:message code="account_password"/>:</label></div>
				<div class="wrapper-widget">
					<form:password id="password" path="password" maxlength="50" />
				</div>
			</div>
			<div><form:errors path="password" cssClass="error" /></div>
			<div class="wrapper-button">
				<form:button ><spring:message code="login"/></form:button>
			</div>
			<div id="openid">
				<c:url var="google_opendid" value="/login-openid.html?op=Google" />
				<c:url var="yahoo_opendid" value="/login-openid.html?op=Yahoo" />
				<a href="${google_opendid}"><img src="<c:url value="/images/google_openid_icon.png"/>" /></a>
				<a href="${yahoo_opendid}"><img src="<c:url value="/images/yahoo_openid_icon.png"/>" /></a>
			</div>	
		</form:form>
	</div>
</div>