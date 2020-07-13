<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>personal</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.gender" var="gender" />
<fmt:message bundle="${loc}" key="local.genderW" var="woman" />
<fmt:message bundle="${loc}" key="local.genderM" var="man" />
<fmt:message bundle="${loc}" key="local.dateOfBirth" var="date" />
<fmt:message bundle="${loc}" key="local.telephone" var="telephone" />
<fmt:message bundle="${loc}" key="local.save" var="save" />
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp" />	

<form action="Controller" method="post">
			<input type="hidden" name="command" value="edit_user_data" /> 
			${name}: <br /> <input type="text" name="name" value="${data.name}" /><br />
		
			${surname}: <br /> <input type="text" name="surname" value="${data.surname}" /><br /> 
			
			${gender}: <br /> <input checked='<c:if test="${data.gender eq m}"></c:if>' type="radio" name="gender" value="m" /> ${man}  
			<input checked='<c:if test="${data.gender eq w}"></c:if>' type="radio" name="gender" value="w" /> ${woman} <br /> 
			
			${date}: (yyyy-mm-dd) <br /> <input type="text" name="dateOfBirth" value="${data.dateOfBirth}" /><br /> 
			
			${telephone}: <br /> <input type="text" name="telephone" value="${data.telephone}" /><br />
			
			<input type="submit" value="${save}" />					
		</form>

</body>
</html>