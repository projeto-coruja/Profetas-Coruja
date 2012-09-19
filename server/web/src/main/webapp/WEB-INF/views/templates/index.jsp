<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>Insert title here</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/reset.css"/>" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>" />
		
		<script src="<c:url value='/js/jquery.js' />" type="text/javascript"></script>
		<script src="<c:url value='/js/account.js' />" type="text/javascript"></script>
	</head>
	<body>

		<div id="header"><div class="container left"><tiles:insertAttribute name="header" /></div></div>
		<div id="content"><div class="container left"><div id="body"><tiles:insertAttribute name="content" /></div></div></div>
		<div id="footer"><div class="container left"><tiles:insertAttribute name="footer" /></div></div>
		<div id="banners"><div class="container left"><tiles:insertAttribute name="banners" /></div></div>

	</body>
</html>