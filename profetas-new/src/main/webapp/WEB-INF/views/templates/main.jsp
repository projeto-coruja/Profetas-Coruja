<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Profetas</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/coruja.css'/>" />
		       
		<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/dropDown.css'/>" />
		
		<script src="<c:url value='/static/js/jquery.js' />" type="text/javascript"></script>		
		<script src="<c:url value='/static/js/jquery.i18n.properties.js' />" type="text/javascript"></script>
		<script src="<c:url value='/static/js/json.min.js' />" type="text/javascript"></script>
		<script src="<c:url value='/static/js/default.js' />" type="text/javascript"></script>
		<script src="<c:url value='/static/js/utility.js' />" type="text/javascript"></script>
    </head>
    <body>
    	<div class="container">
			<div class="header"><tiles:insertAttribute name="header" /></div>
			
			<div id="containerMessages"><tiles:insertAttribute name="messages" /></div>
			
			<div class="sidebar1">
    			<tiles:insertAttribute name="menu" />
    		</div>
    		
			<div class="text">
				<tiles:insertAttribute name="content" />
			</div>
	
			<div class="footer"><tiles:insertAttribute name="footer" /></div>
	
		</div>		
		
    </body>
</html>