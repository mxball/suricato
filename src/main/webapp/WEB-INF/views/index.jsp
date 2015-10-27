<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/index.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/header.css'/>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home page</title>
</head>
<body>
	<%@include file="header.jsp" %>
	<a href='<c:url value="/retrospectiva/nova"/>'>Nova retrospectiva</a>
	<a href='<c:url value="/time/novo"/>'>Novo time</a>
	<fieldset>
		<legend>Retrospectivas em aberto</legend>
		<c:forEach var="retrospectiva" items="${retrospectivasAbertas}">
			<a href='<c:url value="/retrospectiva/mostra?id=${retrospectiva.id}"/>'><img src="<c:url value="${retrospectiva.lousa.endereco}"/>"></a>
		</c:forEach>
	</fieldset>
	<fieldset>
		<legend>Retrospectivas encerradas</legend>
		<c:forEach var="retrospectiva" items="${retrospectivasEncerradas}">
			<a href='<c:url value="/retrospectiva/mostra?id=${retrospectiva.id}"/>'><img src="<c:url value="${retrospectiva.lousa.endereco}"/>"></a>
		</c:forEach>
	</fieldset>
</body>
</html>