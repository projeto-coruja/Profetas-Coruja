<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<table>
	<tr>
		<td width="10%"><a href="<c:url value="/"/>"><img src="<c:url value="/images/project_logo.png"/>" class="project-logo" /></a></td>
		<td width="60%">
			<div class="project_name"><spring:message code="project_name"/></div>				
			<div class="project_fullname"><spring:message code="project_name_full"/><span class="project_version"><spring:message code="project_beta_disclaimer"/></span></div>
		</td>
		<td width="30%" valign="bottom">
			<c:if test="${empty currentUser.username}">
				<a href="<c:url value="/login.html"/>" class=""><spring:message code="login"/></a> | <a href="<c:url value="/create-account.html"/>" class=""><spring:message code="sign_up"/></a>
			</c:if>
			<c:if test="${not empty currentUser.username}">
				<ul id="usermenu">
					<li class="last">${currentUser.username}
						<ul>
							<li><a href="#">Account</a></li>
							<li class="last"><a href="#">Groups</a></li>
						</ul>						
					</li>
				</ul>
			</c:if>			
		</td>		
	</tr>
</table>