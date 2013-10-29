<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="side-login">
	<sec:authorize access="!isAnonymous()">
		Bem vindo <sec:authentication property="principal.username"/>
		<a href="<c:url value="/logout.html" />" class="center button"><spring:message code="lbl_logout"/></a>
		<!--a href="<c:url value="j_spring_security_logout" />" class="center button"><spring:message code="lbl_logout"/></a-->
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		<form id="loginForm" action="<c:url value='/'/>j_spring_security_check" method="post">	
			<div class="login-label-box">
		        <label for="username"><spring:message code="lbl_user"/></label>
		    </div>
		    <div class="login-input-box">
		        <input type="text" class="input-login" id="username" name="j_username"/>			
			</div>
			
			<div class="login-label-box">
		        <label for="password"><spring:message code="lbl_password"/></label>
			</div>
			<div class="login-input-box">
		        <input type="password" class="input-login" id="password" name="j_password"/>	
			</div>
			
	        <div class="login-button-box">
	        	<button id="loginUser" class="button" name="submit" type="submit"><spring:message code="lbl_sign_in"/></button>
	        	<a href="<c:url value='/register-user.html'/>" id="registerUser" class="button"><spring:message code="lbl_sign_up"/></a>
	       	</div>
	       	
	       	<div class="center">
	       		<a href="<c:url value='/remember-pass.html'/>" class=""><spring:message code="lbl_remember_pass"/></a>
	       	</div>
	    </form>
    </sec:authorize>
</div>