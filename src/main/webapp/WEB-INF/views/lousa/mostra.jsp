<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div id="lousa">
			<img id="atividade" src="<c:url value="${lousa.endereco}"/>"/>
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
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/lousa/lousa.js'/>"></script>
</html>