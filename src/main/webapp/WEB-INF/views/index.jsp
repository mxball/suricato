<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="<c:url value='/assets/css/reset.css'/>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.postIt {
	width: 100px;
	height: 100px;
	background-color: yellow;       
}
#lousa {
	width: 100vw;
	height: 100vh;
}
#lousa img {
	width: 100%;
	height: 100%;
}
</style>
<title>Home page</title>
</head>
<body>
	<div id="lousa">
		<img src="<c:url value="/assets/images/gladSad.png"/>"/>
	</div>
	<script type="text/javascript">
		function getMessage(coordenadas) {
			var comentario = prompt("Adicionar Post-it: comentário");
			printPostIt(comentario, coordenadas);
		}
		function printPostIt(mensagem, coordenadas) {
			var cursorX = coordenadas.clientX;
			var cursorY = coordenadas.clientY;
			
			var postIt = document.createElement('div');
			postIt.classList.add("postIt");
			postIt.style.position = "absolute";
			console.log(cursorX + " " + cursorX)
			postIt.style.left = cursorX + "px";
			postIt.style.top = cursorY + "px";

			var texto = document.createElement('p');
			texto.textContent = mensagem;

			postIt.appendChild(texto);
			document.body.appendChild(postIt);
		}
		document.querySelector("#lousa").addEventListener("click", getMessage);
	</script>
</body>
</html>