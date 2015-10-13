<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/index.css'/>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home page</title>
</head>
<body>
	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Logout">
	</form>
	<a href='<c:url value="/retrospectiva/nova"/>'>Nova retrospectiva</a>
	<c:forEach var="retrospectiva" items="${retrospectivas}">
		<a href='<c:url value="/retrospectiva/mostra?id=${retrospectiva.id}"/>'><img src="<c:url value="${retrospectiva.lousa.endereco}"/>"></a>
	</c:forEach>
</body>
</html>