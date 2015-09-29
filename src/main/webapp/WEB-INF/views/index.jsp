<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.postIt {
	width: 100px;
	height: 100px;
	background-color: yellow;	
}
#lousa {
	
}
#campo-feliz, #campo-triste{
	width: 49%; 
	height: 59%;
	display: inline-block;
	border-color: #000;
	border-width: 5px;
	border-style: solid;
	margin: -5px;
}
#campo-duvidas {
	width: 99%;
	height: 40%;
	border-color: #000;
	border-width: 5px;
	border-style: solid;
	margin: -5px;
}


</style>
<title>Home page</title>
</head>
<body>
	<div id="lousa">
		<canvas id="campo-feliz"></canvas>
		<canvas id="campo-triste"></canvas>
		<canvas id="campo-duvidas"></canvas>
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
		document.addEventListener("click", getMessage);
	</script>
</body>
</html>