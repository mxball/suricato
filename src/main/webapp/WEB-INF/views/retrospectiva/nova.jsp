<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Nova retrospectiva</title>
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/retrospectiva/cria.css'/>">
	</head>
	<body>
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Logout">
		</form>
		<div id="formLousa">
		<form:form role="form" commandName="retrospectiva" servletRelativeAction="/retrospectiva/cria">
			<input type="hidden" name="criador.nome" value="${pageContext.request.userPrincipal.name}"/>
			<select name="lousa.id"  id="idLousa" onchange="mostraLousa()">
				<c:forEach items="${lousas}" var="lousa" varStatus="status">
					<option value="${lousa.id}" id="atividade_${status.index}" data-url="${lousa.endereco}">${lousa.nome}</option>
				</c:forEach>
			</select>
			<img id="imagem" src=""/>
			<button type="submit">Criar</button>
		</form:form>
		</div>
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/nova.js'/>"></script>
</html>