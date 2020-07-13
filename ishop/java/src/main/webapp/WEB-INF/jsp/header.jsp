<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.bookShop" var="bookShop" />
<fmt:message bundle="${loc}" key="local.signIn" var="signIn" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.main" var="main" />
<fmt:message bundle="${loc}" key="local.catalog" var="catalog" />
<fmt:message bundle="${loc}" key="local.signIn" var="signIn" />
<fmt:message bundle="${loc}" key="local.cart" var="cart" />
<fmt:message bundle="${loc}" key="local.signOut" var="signOut" />
</head>

<body>


	<h1>${bookShop}</h1>

	<table border="0" width="100%">
		<tr>
			<td width="10%"><a href="Controller?command=go_to_main">${main}</a>
			</td>
			<td width="20%"><a href="Controller?command=go_to_catalog&catalog_level=category">${catalog}</a>
			</td>

			<c:if test="${user != null}">
			
				<td width="10%"><a href="Controller?command=go_to_cart">${cart}</a>
				</td>
				<td width="20%"> 
					<c:if test="${user.name != null}">
						<a href="Controller?command=go_to_personal">${user.name}</a> | 
					</c:if>
					<c:if test="${user.name == null}">
						<a href="Controller?command=go_to_personal">User</a> | 
					</c:if>
				<a href="Controller?command=sign_out">${signOut}</a>
				</td>
			</c:if>

			<c:if test="${user == null}">
				<td width="10%"><a href="Controller?command=go_to_login">${signIn}</a>

				</td>
			</c:if>
			<td width="10%">

				<form action="Controller" method="post">
					<input type="hidden" name="command" value="change_locale" /> <input
						type="hidden" name="local" value="ru" /> <input type="submit"
						value="${ru_button}" /><br />
				</form>

				<form action="Controller" method="post">
					<input type="hidden" name="command" value="change_locale" /> <input
						type="hidden" name="local" value="en" /> <input type="submit"
						value="${en_button}" /><br />
				</form>
			</td>
		</tr>
	</table>

</body>
</html>