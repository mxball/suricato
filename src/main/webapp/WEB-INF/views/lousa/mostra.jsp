<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-impromptu.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-ui.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/lousa/postIt.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/lousa/lousa.css'/>">
		<title>Retrospectiva</title>
	</head>
	<body>
		<div id="lousa">
			<img id="atividade" src="<c:url value="${lousa.endereco}"/>"/>
			<div id="menu">
				<div class="novoPostIt corFCF0AD"></div>
				<div class="novoPostIt corE9E74A"></div>
				<div class="novoPostIt corD0E17D"></div>
				<div class="novoPostIt cor56C4E8"></div>
				<div class="novoPostIt corCDDD73"></div>
				<div class="novoPostIt cor99C7BC"></div>
				<div class="novoPostIt corF9D6AC"></div>
				<div class="novoPostIt corBAB7A9"></div>
				<div class="novoPostIt corFFFFFF"></div>
			</div>
		</div>
<!-- 		<div id="opcao"> -->
<!-- 			<input type="radio" name="opcao-adiciona" class="adiciona" id="adiciona-postit" checked> -->
<!-- 			<label for="adiciona-postit"></label> -->
<!-- 			<input type="radio" name="opcao-adiciona" class="adiciona" id="adiciona-texto"> -->
<!-- 			<label for="adiciona-texto"></label> -->
<!-- 		</div> -->
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/lousa/lousa.js'/>"></script>
	<script src="<c:url value='/assets/js/lousa/postIt.js'/>"></script>
</html>