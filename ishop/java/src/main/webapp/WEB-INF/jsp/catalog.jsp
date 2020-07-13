<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>catalog</title>
</head>
<body>

	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<c:if test="${type eq 'category'}">

		<c:forEach var="category" items="${categories}">
			<a
				href="Controller?command=go_to_catalog&catalog_level=subcategory&category_id=${category.id}">${category.name}</a>
			<br />

		</c:forEach>
	</c:if>
	
	<c:if test="${type eq 'subcategory'}">

		<c:forEach var="category" items="${categories}">
			<a
				href="Controller?command=go_to_product_list&catalog_level=subcategory&category_id=${category.id}">${category.name}</a>
			<br />

		</c:forEach>
	</c:if>
</body>
</html>