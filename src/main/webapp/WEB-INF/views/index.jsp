<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="suricato" %>
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
	<suricato:listaRetrospectivas retrospectivas="${usuario.retrospectivasAbertas}" legenda="Retrospectivas em aberto"/>
	<suricato:listaRetrospectivas retrospectivas="${usuario.retrospectivasEncerradas}" legenda="Retrospectivas encerradas"/>
	<c:forEach var="time" items="${usuario.times}">
		<suricato:listaRetrospectivas retrospectivas="${time.retrospectivasAbertas}" legenda="Retrospectivas abertas do ${time.nome}"/>
		<suricato:listaRetrospectivas retrospectivas="${time.retrospectivasEncerradas}" legenda="Retrospectivas encerradas do ${time.nome}"/>
	</c:forEach>
</body>
</html>