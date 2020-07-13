<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>cart</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.pieces" var="pieces" />


</head>
<body>

	<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<table border="0" width="100%">
		<c:forEach var="product" items="${productList}">
			<tr>
				<td width="5%"><input type="checkbox" name="${product.id}" /></td>
				<td width="10%"><img src="${product.image}" width="60"
					height="90"></td>
				<td width="45%"><a
					href="Controller?command=go_to_product&prod_id=${product.id}">${product.name}</a><br />
					${product.author}</td>
				<td width="10%">${product.price}p</td>
				<td width="10%"><input type="text" name="amount" size="1"
					value="1" /> ${pieces}</td>
				<td width="20%">
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="delete_from_cart" />
						<input type="hidden" name="prod_id" value="${product.id}" /> 
						<input type="submit" value="Удалить" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>