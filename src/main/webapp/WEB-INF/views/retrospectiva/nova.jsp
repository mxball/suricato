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
		<form:form role="form" commandName="retrospectiva" servletRelativeAction="/retrospectiva/cria">
			<input type="hidden" name="criador.nome" value="${pageContext.request.userPrincipal.name}"/>
			<c:forEach items="${lousas}" var="lousa" varStatus="status">
				<input type="radio" name="lousa.id" value="${lousa.id}" id="atividade_${status.index}"/>
				<div class="atividades">
					<label for="atividade_${status.index}">
						<h2>${lousa.nome}</h2>
						<img src="<c:url value="${lousa.endereco}"/>"><br/>
					</label>
				</div>
			</c:forEach>
			<button type="submit">Criar</button>
		</form:form>
	</body>
</html>