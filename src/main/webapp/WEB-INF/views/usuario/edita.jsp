<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="suricato" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Nova retrospectiva</title>
		<link rel="icon" type="image/png" href="<c:url value='/assets/images/logo.png'/>" />
		<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
		<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
		<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/corpo.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/menu.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-impromptu.min.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery-ui.css'/>">
		<link href="https://fonts.googleapis.com/css?family=Montserrat:800" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
		<link rel="stylesheet" href="<c:url value='/assets/css/cadastro.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/usuario/edita.css'/>">
	</head>
	<body>
		<%@include file="../header.jsp" %>
		<div class="menu">
			<a href="/" class="menu-opcao --retro"><fmt:message key="retrospective"/></a>
			<a href="/usuario/edita" class="menu-opcao --perfil --selecionado"><fmt:message key="user.edit"/></a>
		</div>
		<div class="corpo">
			<span class="corpo-sucesso">${atualizado}</span>
			<form action="<c:url value='/usuario/edita'/>" class="cadastro" method="post" enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="cadastro-foto foto">
					<input type="file" name="file" id="foto" class="cadastro-foto_campo foto-arquivo"/>
					<img alt="foto-perfil" src="<c:url value='/usuario/perfil'/>" class="cadastro-foto_imagem foto-visualiza">
					<label for="foto" class="cadastro-foto_descricao foto-texto"><fmt:message key="user.photo.update"/></label>
				</div>
				<div class="cadastro-dados">				
					<label class="cadastro-descricao" for="nome">
						<fmt:message key="user.name"/> <form:errors path='usuario.nome' class="cadastro-erro"/>
					</label>
					<input type='text' name='nome' id="nome" class="cadastro-campo" value="${usuario.nome}" placeholder="<fmt:message key="user.name"/>"/>
	
					<label class="cadastro-descricao" for="email">
						<fmt:message key="user.email"/> <form:errors path='usuario.email' class="cadastro-erro"/>
					</label>
					<input type='text' name='email' id="email" class="cadastro-campo" value="${usuario.email}" placeholder="<fmt:message key="user.email"/>"/>
	
					<label class="cadastro-descricao" for="senha">
						<fmt:message key="user.password"/> <form:errors path='usuario.senha' class="cadastro-erro"/>
					</label>
					<input type='password' name='senha' id="senha" class="cadastro-campo" placeholder="<fmt:message key="user.password.new"/>"/>
	
					<label class="cadastro-descricao" for="confirmaSenha">
						<fmt:message key="user.confirm.password"/> <form:errors path='usuario.confirmaSenha' class="cadastro-erro"/>
					</label>
					<input type='password' name='confirmaSenha' id="confirmaSenha" class="cadastro-campo" placeholder="<fmt:message key="user.confirm.password"/>"/>
					
					<button type="submit" class="cadastro-botao"><fmt:message key="user.save.button"/></button>
				</div>
			</form>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
	</body>
	<script src="<c:url value='/assets/js/jquery-2.1.4.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-impromptu.js'/>"></script>
	<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
	<script src="<c:url value='/assets/js/foto.js'/>"></script>
</html>	