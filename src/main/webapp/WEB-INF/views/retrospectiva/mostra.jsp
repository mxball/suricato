<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-impromptu.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-ui.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/retrospectiva/lousa.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/retrospectiva/postIt.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/retrospectiva/comentario.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/header.css'/>">
		<title>Retrospectiva</title>
	</head>
	<body>
		<div id="lousa" data-usuario-nome="${pageContext.request.userPrincipal.name}" data-retrospectiva-id="${retrospectiva.id}">
			<img id="atividade" src="<c:url value="${lousa.endereco}"/>"/>
			<c:forEach var="postIt" items="${postIts}" varStatus="status">
				<div id="postIt_${postIt.id}" class="postIt comConteudo ${postIt.cor}" style="left: ${postIt.posicaoHorizontal}%; top: ${postIt.posicaoVertical}%;">
					<a href="#" class="editar"></a>
					<a href="#" class="remover"></a>
					<a href="#" class="like default">${postIt.numeroVotos}</a>
					<a href="#" class="dislike default">${postIt.numeroDeslikes}</a>
					<p class="conteudo">${postIt.conteudo}</p>
				</div>
			</c:forEach>
			<c:forEach var="comentario" items="${comentarios}" varStatus="status">
				<div id="comentario_${comentario.id}" class="comentario comConteudo" style="left: ${comentario.posicaoHorizontal}%; top: ${comentario.posicaoVertical}%; width: ${comentario.largura}px; min-height: ${comentario.altura}px;">
					<a href="#" class="editar"></a>
					<a href="#" class="remover"></a>
					<p class="conteudo">${comentario.conteudo}</p>
				</div>
			</c:forEach>
			<c:if test="${retrospectiva.aberta}">
				<div id="menu">
					<a id="logoMenu" href="<c:url value="/"/>"><img alt="suricato-logo" src='<c:url value="/assets/images/logo.svg"/>'></a>
					<div class="draggable semConteudo postIt corFCF0AD"></div>
					<div class="draggable semConteudo postIt corE9E74A"></div>
					<div class="draggable semConteudo postIt corD0E17D"></div>
					<div class="draggable semConteudo postIt cor56C4E8"></div>
					<div class="draggable semConteudo postIt cor99C7BC"></div>
					<div class="draggable semConteudo postIt corF9D6AC"></div>
					<div class="draggable semConteudo postIt corBAB7A9"></div>
					<div class="draggable semConteudo comentario"><span>Comentário</span></div>
				</div>
			</c:if>
		</div>
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/sockjs-0.3.min.js'/>"></script>
	<script src="<c:url value='/assets/js/stomp.min.js'/>"></script>
	<script src="<c:url value='/assets/js/retrospectiva/websocket.js'/>"></script>
	<script src="<c:url value='/assets/js/retrospectiva/lousa.js'/>"></script>
</html>