<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="suricato" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Nova retrospectiva</title>
		<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/corpo.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-impromptu.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-ui.css'/>">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
		<link rel="stylesheet" href="<c:url value='/assets/css/cadastro.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/usuario/edita.css'/>">
	</head>
	<body>
		<%@include file="../header.jsp" %>
		<div class="corpo">
			<h2 class="corpo-descricao">Editar Perfil</h2>
			<span class="corpo-sucesso">${atualizado}</span>
			<form action="<c:url value='/usuario/edita'/>" class="cadastro" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<label class="cadastro-descricao" for="nome">
					Nome <form:errors path='usuario.nome' class="cadastro-erro"/>
				</label>
				<input type='text' name='nome' id="nome" class="cadastro-campo" value="${usuario.nome}" placeholder="Nome do usuário"/>

				<label class="cadastro-descricao" for="email">
					E-mail <form:errors path='usuario.email' class="cadastro-erro"/>
				</label>
				<input type='text' name='email' id="email" class="cadastro-campo" value="${usuario.email}" placeholder="E-mail do usuário"/>

				<label class="cadastro-descricao" for="senha">
					Senha <form:errors path='usuario.senha' class="cadastro-erro"/>
				</label>
				<input type='password' name='senha' id="senha" class="cadastro-campo" placeholder="Nova senha"/>

				<label class="cadastro-descricao" for="confirmaSenha">
					Repetir senha <form:errors path='usuario.confirmaSenha' class="cadastro-erro"/>
				</label>
				<input type='password' name='confirmaSenha' id="confirmaSenha" class="cadastro-campo" placeholder="Repetir a senha"/>
				
				<button type="submit" class="cadastro-botao">CRIAR</button>
			</form>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/nova.js'/>"></script>
</html>	