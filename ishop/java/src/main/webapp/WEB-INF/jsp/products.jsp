<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>products</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.addToCart" var="add_to_cart" />
<fmt:message bundle="${loc}" key="local.inCart" var="in_cart" />
</head>
<body>

	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<table border="0" width="100%">
		<c:forEach var="product" items="${productList}">
			<tr>
				<td width="10%"><img src="${product.image}" width="60"
					height="90"></td>
				<td width="45%"><a
					href="Controller?command=go_to_product&prod_id=${product.id}">${product.name}</a><br />
					${product.author}</td>
				<td width="10%">${product.price} p</td>
				<td width="20%">
					<c:set var="access" scope="page" value="0" />
					<c:forEach var="cart_product_id" items="${cart.products}">
						<c:if test="${product.id eq cart_product_id}">
							<c:set var="access" scope="page" value="1" />
						</c:if>
					</c:forEach>
					
					<c:if test="${access eq '0' and user ne null}">
						<form action="Controller" method="post">
							<input type="hidden" name="command" value="add_to_cart" /> 
							<input type="hidden" name="prod_id" value="${product.id}" /> 
							<input type="submit" value="${add_to_cart}" />
						</form>
					</c:if>
					<c:if test="${access eq '1'}">
					${in_cart}
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>