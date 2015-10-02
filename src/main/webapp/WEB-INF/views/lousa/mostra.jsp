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
		</div>
		<div id="opcao">
			<input type="radio" name="opcao-adiciona" class="adiciona" id="adiciona-postit" checked>
			<label for="adiciona-postit"></label>
			<input type="radio" name="opcao-adiciona" class="adiciona" id="adiciona-texto">
			<label for="adiciona-texto"></label>
		</div>
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/lousa/lousa.js'/>"></script>
	<script src="<c:url value='/assets/js/lousa/postIt.js'/>"></script>
</html>