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
		<link rel="stylesheet" href="<c:url value='/assets/css/lousa/lousa.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/lousa/postIt.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/lousa/comentario.css'/>">
		<title>Retrospectiva</title>
	</head>
	<body>
		<form:form role="form" id="retrospectiva" modelAttribute="retrospectiva" servletRelativeAction="/retrospectiva/salvar" method="POST">
			<input type="hidden" name="id" value="${retrospectiva.id}"/>
			<input type="hidden" name="criador.id" value="${retrospectiva.criador.id}"/>
			<div id="lousa">
				<img id="atividade" src="<c:url value="${retrospectiva.lousa.endereco}"/>"/>
				<input type="hidden" name="lousa.id" value="${retrospectiva.lousa.id}"/>
				<input type="hidden" name="lousa.endereco" value="${retrospectiva.lousa.endereco}"/>
				<c:forEach var="postIt" items="${retrospectiva.postIts}" varStatus="status">
					<div class="postIt corFCF0AD" style="left: ${postIt.posicaoHorizontal}%; top: ${postIt.posicaoVertical}%;">
						<a href="#" onclick="removeElemento" class="remover"></a>
						<p class="conteudo">${postIt.conteudo}</p>
						<input type="hidden" name="postIts[${status.index}].id" value="${postIt.id}"/>
						<input type="hidden" name="postIts[${status.index}].conteudo" value="${postIt.conteudo}"/>
						<input type="hidden" name="postIts[${status.index}].posicaoHorizontal" value="${postIt.posicaoHorizontal}"/>
						<input type="hidden" name="postIts[${status.index}].posicaoVertical" value="${postIt.posicaoVertical}"/>
						<input type="hidden" name="postIts[${status.index}].cor" value="${postIt.cor}"/>
					</div>
				</c:forEach>
				<c:forEach var="comentario" items="${retrospectiva.comentarios}">
					<div class="comentario">
						<a href="#" onclick="removeElemento" class="remover"></a>
						<p class="conteudo">${comentario.conteudo}</p>
						<input type="hidden" name="comentarios[${status.index}].conteudo" value="${comentario.conteudo}"/>
						<input type="hidden" name="comentarios[${status.index}].posicaoHorizontal" value="${comentario.posicaoHorizontal}"/>
						<input type="hidden" name="comentarios[${status.index}].posicaoVertical" value="${comentario.posicaoVertical}"/>
					</div>
				</c:forEach>
				<div id="menu">
					<div class="draggable semConteudo postIt corFCF0AD"></div>
					<div class="draggable semConteudo postIt corE9E74A"></div>
					<div class="draggable semConteudo postIt corD0E17D"></div>
					<div class="draggable semConteudo postIt cor56C4E8"></div>
					<div class="draggable semConteudo postIt corCDDD73"></div>
					<div class="draggable semConteudo postIt cor99C7BC"></div>
					<div class="draggable semConteudo postIt corF9D6AC"></div>
					<div class="draggable semConteudo postIt corBAB7A9"></div>
					<div class="draggable semConteudo comentario"><span>Comentário</span></div>
				</div>
			</div>
			<input type="submit" value="Salvar"/>
		</form:form>
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/lousa/lousa.js'/>"></script>
</html>