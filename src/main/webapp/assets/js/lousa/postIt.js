function cuteOkCancelPrompt(msg, callbackFunction, cancelCallback) {
	prompt = $.prompt(msg , {
		buttons:{Ok:true,Cancel:false},
		submit: function(e,v,m,f){
			if(v == true) {
				callbackFunction(m);
			} else {
				if(cancelCallback != null) {
					cancelCallback(m);
				}
			}
		}
	} );
}

function createInput(type, name, id, value) {
	var input = document.createElement('input');
	input.type = type;
	input.name = name;
	input.id = id;
	input.value = value;
	return input;
}

function createRadioCor(cor) {
	var div = document.createElement('div')
	div.className = "campo-cor " + cor;
	div.appendChild(createInput("radio", "cor", "cor_" + cor, cor))
	return div;
}

var cores = ["FCF0AD", "E9E74A", "D0E17D", "56C4E8", "CDDD73", "99C7BC", "F9D6AC", "BAB7A9"];

function getMessage(coordenadas) {
	var popup = document.createElement("fildset");
	var legenda = document.createElement("legend");
	legenda.textContent = "Novo Post-It";
	popup.appendChild(legenda);
	
	var comentario = document.createElement("p");
	comentario.textContent = "Comentário"
	popup.appendChild(comentario);
	
	var inputComentario = createInput("text", "comentario");
	popup.appendChild(inputComentario);
	
	var textoCor = document.createElement("p");
	textoCor.textContent = "Cor"
	popup.appendChild(textoCor);
	
	Array.prototype.forEach.call(cores, function(cor, index) {
		popup.appendChild(createRadioCor(cor));
		popup.appendChild(document.createElement("br"));
	});
	
	cuteOkCancelPrompt(popup.outerHTML, function(result) {
		var comentario = result.find("input[name='comentario']").val();
		var cor = result.find("input[name='cor']:checked").val();
		console.log(comentario + " " + cor);
		printPostIt(comentario, cor, coordenadas);
	});
}

function printPostIt(mensagem, cor, coordenadas) {
	var cursorX = (coordenadas.clientX * 100.0) / window.innerWidth;
	var cursorY = (coordenadas.clientY * 100.0) / window.innerHeight;
	var postIt = document.createElement('div');
	postIt.classList.add("postIt");

	postIt.style.left = cursorX + "%";
	postIt.style.top = cursorY + "%";
	postIt.classList.add(cor);
	
	var texto = document.createElement('p');
	texto.textContent = mensagem;

	postIt.appendChild(texto);
	document.querySelector("#lousa").appendChild(postIt);
}
document.querySelector("#lousa").addEventListener("click", getMessage);