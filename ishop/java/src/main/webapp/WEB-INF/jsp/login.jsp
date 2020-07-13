<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="localization.local" var="loc" />
	
	<fmt:message bundle="${loc}" key="local.enterEmail" var="enter_email" />
	<fmt:message bundle="${loc}" key="local.enterPassword" var="enter_password" />
	<fmt:message bundle="${loc}" key="local.registration" var="registration" />
	<fmt:message bundle="${loc}" key="local.signIn" var="signIn" />
	<fmt:message bundle="${loc}" key="local.repeatPassword" var="repeat_password" />
	<fmt:message bundle="${loc}" key="local.userNotFound" var="user_not_found" />
	<fmt:message bundle="${loc}" key="local.userExists" var="user_already_exists" />
	<fmt:message bundle="${loc}" key="local.goodRegistration" var="registration_ok" />
	<fmt:message bundle="${loc}" key="local.emptyFields" var="fields_are_empty" />
	<fmt:message bundle="${loc}" key="local.wrongSecondPass" var="second_password_is_wrong" />
	<fmt:message bundle="${loc}" key="local.wrongEmail" var="email_is_wrong" />
</head>

<body>

<jsp:include page="/WEB-INF/jsp/header.jsp" />	
	
<c:choose>	
	<c:when test="${form eq 'authorization'}">	
	<c:if test="${param.authstatus eq 'nullFields'}">
	${fields_are_empty}
	</c:if>
	<c:if test="${param.authstatus eq 'wrong'}">
	${user_not_found}
	</c:if>
	<c:if test="${param.regstatus eq 'ok'}">
	${registration_ok}
	</c:if>
	<c:if test="${param.authstatus eq 'wrongEmail'}">
	${email_is_wrong}
	</c:if>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="authorization" /> 
			${enter_email}: <br /> <input type="text" name="email" value="" /><br />
		
			${enter_password}: <br /> <input type="password" name="password" value="" /><br /> 
			
			<input type="submit" value="${signIn}" />					
		</form>
				
		<br /><a href="Controller?command=change_form">${registration}</a>
				
	</c:when>
	
	<c:when test="${form eq 'registration'}">
	<c:if test="${param.regstatus eq 'error'}">
	${user_already_exists}
	</c:if>
	<c:if test="${param.regstatus eq 'nullFields'}">
	${fields_are_empty}
	</c:if>
	<c:if test="${param.regstatus eq 'wrongSecondPass'}">
	${second_password_is_wrong}
	</c:if>
	<c:if test="${param.regstatus eq 'wrongEmail'}">
	${email_is_wrong}
	</c:if>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="registration" /> 
			${enter_email}: <br /> <input type="text" name="email" value="" /><br />
		
			${enter_password}: <br /> <input type="password" name="password1" value="" /><br /> 
		
			${repeat_password}: <br /> <input type="password" name="password2" value="" /><br /> 
			
		<input type="submit" value="${registration}" />
		</form>
	</c:when>
</c:choose>	
</body>
</html>