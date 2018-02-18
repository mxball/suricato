<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="suricato" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/corpo.css'/>">
	<link rel="stylesheet" href="<c:url value='/assets/css/menu.css'/>">
	<link href="https://fonts.googleapis.com/css?family=Montserrat:800" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home page</title>
</head>
<body>
	<%@include file="header.jsp" %>
	<div class="menu">
		<a href="/" class="menu-opcao --retro --selecionado">Retrospectivas</a>
		<a href="/usuario/edita" class="menu-opcao --perfil">Editar Perfil</a>
	</div>
	<div class="corpo">
		<c:choose>
			<c:when test="${mapaTimes.comRetrosPassadas}">
				<%@include file="home.jsp" %>
			</c:when>
			<c:otherwise>
				<%@include file="iniciante.jsp" %>
			</c:otherwise>
		</c:choose>
	</div>
	<%@include file="footer.jsp" %>
</body>
</html>