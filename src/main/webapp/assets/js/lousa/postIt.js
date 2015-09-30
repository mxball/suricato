function getMessage(coordenadas) {
		var comentario = prompt("Novo Post-it");
		if(comentario != "") {
			printPostIt(comentario, coordenadas);
		}
	}
	function printPostIt(mensagem, coordenadas) {
		var cursorX = coordenadas.clientX;
		var cursorY = coordenadas.clientY;
		
		var postIt = document.createElement('div');
		postIt.classList.add("postIt");
		postIt.style.left = cursorX + "px";
		postIt.style.top = cursorY + "px";

		var texto = document.createElement('p');
		texto.textContent = mensagem;

		postIt.appendChild(texto);
		document.querySelector("#lousa").appendChild(postIt);
	}
	document.querySelector("#lousa").addEventListener("click", getMessage);