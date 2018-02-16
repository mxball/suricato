<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/css/usuario/login.css'/>">
		<link href="https://fonts.googleapis.com/css?family=Montserrat:800" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
		<title>Novo usuário</title>
	</head>
	<body>
		<fieldset class="login">
			<img alt="Logo suricato" src="<c:url value="/assets/images/logo-suricato.svg"/>" class="login-titulo">
			<form action="<c:url value='/usuario/cadastro'/>" class="login-usuario" method="post" enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="file" name="file" id="foto" class="login-usuario_arquivo foto-arquivo"/>
				<img alt="foto-perfil" src="<c:url value='/assets/images/defaultUser.png'/>" class="login-usuario_perfil foto-visualiza">
				<label for="foto" class="login-usuario_selecionarFoto foto-texto">Escolher foto</label>
				<form:errors path='usuario.nome' cssClass="login-usuario_error"/>
				<input type='text' name='nome' class="login-usuario_dado" value="${cadastroUsuarioDTO.nome}" placeholder="Usuário"/>
				<form:errors path='usuario.email' cssClass="login-usuario_error"/>
				<input type='text' name='email' class="login-usuario_dado" value="${cadastroUsuarioDTO.email}" placeholder="E-mail"/>
				<form:errors path='senha' cssClass="login-usuario_error"/>
				<form:errors path='usuario.senha' cssClass="login-usuario_error"/>
				<input type='password' name='senha' class="login-usuario_dado" placeholder="Senha"/>
				<form:errors path='usuario.confirmaSenha' cssClass="login-usuario_error"/>
				<input type='password' name='confirmaSenha' class="login-usuario_dado" placeholder="Confirme sua senha"/>
				<button type="submit" class="login-usuario_botao">CADASTRAR</button>
			</form>
			<span class="login-usuario_acao">
				Já possui cadastro? <a href='<c:url value="/login"/>' class="--link">Volte para o login</a>
			</span>
		</fieldset>
	</body>
	<script src="<c:url value='/assets/js/foto.js'/>"></script>
</html>