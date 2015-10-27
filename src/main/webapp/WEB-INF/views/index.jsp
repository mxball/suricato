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
	<c:forEach var="time" items="${usuario.times}">
		<a href='<c:url value="/time/mostra?id=${time.id}"/>'>${time.nome}</a>
	</c:forEach>
	<fieldset>
		<legend>Retrospectivas em aberto</legend>
		<c:forEach var="retrospectiva" items="${usuario.retrospectivasAbertas}">
			<a href='<c:url value="/retrospectiva/mostra?id=${retrospectiva.id}"/>'><img src="<c:url value="${retrospectiva.lousa.endereco}"/>"></a>
		</c:forEach>
	</fieldset>
	<fieldset>
		<legend>Retrospectivas encerradas</legend>
		<c:forEach var="retrospectiva" items="${usuario.retrospectivasEncerradas}">
			<a href='<c:url value="/retrospectiva/mostra?id=${retrospectiva.id}"/>'><img src="<c:url value="${retrospectiva.lousa.endereco}"/>"></a>
		</c:forEach>
	</fieldset>
	<c:forEach var="time" items="${usuario.times}">
		<c:if test="${not empty time.retrospectivasAbertas}">
			<fieldset>
				<legend>Retrospectivas abertas do ${time.nome}</legend>
				<c:forEach var="retrospectiva" items="${time.retrospectivasAbertas}">
					<a href='<c:url value="/retrospectiva/mostra?id=${retrospectiva.id}"/>'><img src="<c:url value="${retrospectiva.lousa.endereco}"/>"></a>
				</c:forEach>
			</fieldset>
		</c:if>
		<c:if test="${not empty time.retrospectivasEncerradas}">
			<fieldset>
				<legend>Retrospectivas encerradas do ${time.nome}</legend>
				<c:forEach var="retrospectiva" items="${time.retrospectivasEncerradas}">
					<a href='<c:url value="/retrospectiva/mostra?id=${retrospectiva.id}"/>'><img src="<c:url value="${retrospectiva.lousa.endereco}"/>"></a>
				</c:forEach>
			</fieldset>
		</c:if>
	</c:forEach>
</body>
</html>