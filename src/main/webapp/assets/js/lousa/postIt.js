function getMessage(coordenadas) {
		var comentario = prompt("Novo Post-it");
		if(comentario != "") {
			printPostIt(comentario, coordenadas);
		}
	}
	function printPostIt(mensagem, coordenadas) {
		var cursorX = (coordenadas.clientX * 100.0) / window.innerWidth;
		var cursorY = (coordenadas.clientY * 100.0) / window.innerHeight;
		console.log(cursorX + ", " + cursorY);
		var postIt = document.createElement('div');
		postIt.classList.add("postIt");
		
		
		postIt.style.left = cursorX + "%";
		postIt.style.top = cursorY + "%";

		var texto = document.createElement('p');
		texto.textContent = mensagem;

		postIt.appendChild(texto);
		document.querySelector("#lousa").appendChild(postIt);
	}
	document.querySelector("#lousa").addEventListener("click", getMessage);