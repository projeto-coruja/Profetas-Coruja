<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:if test="${not empty msg_error}">
	<div class="msg_error">
		<c:out value="${msg_error}" escapeXml="false"/>
	</div>
</c:if>

<c:if test="${not empty msg_success}">
	<div class="msg_success">
		<c:out value="${msg_success}" escapeXml="false"/>
	</div>
</c:if>