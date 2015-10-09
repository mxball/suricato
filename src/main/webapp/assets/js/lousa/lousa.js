var numero = 10;

document.querySelector("#lousa").addEventListener("click", getAcao);

function getAcao(event) {
	$(".postIt").draggable();
	$(".comentario").draggable();
};

$(".draggable").draggable({
	helper: "clone"
});

$("#atividade").droppable({
	accept: ".draggable",
	drop: function (event, ui) {
		var draggable = $(ui.draggable).clone();
		draggable.removeClass("draggable");
		draggable.children().remove();
		var posicaoEsquerda = (ui.offset.left * 100.0) / window.innerWidth;
		var posicaoTopo = (ui.offset.top * 100.0) / window.innerHeight;
		draggable.css('left', posicaoEsquerda + "%");
		draggable.css('top', posicaoTopo + "%");
		var limiteCaracteres = draggable.hasClass("postIt") ? 100 : 0;
		var comentario = createTextarea("conteudo", limiteCaracteres);
		comentario.addEventListener('keydown', teclado);
		draggable.append(comentario);
		draggable.appendTo($(this).parent());
    }
});

function teclado(event) {
	if (event.keyCode == 13) {
		var linkRemove = document.createElement('a');
		linkRemove.onclick = removeElemento;
		linkRemove.href = "#";
		linkRemove.classList.add("remover");
		var texto = document.createElement('p');
		texto.classList.add("conteudo");
		texto.textContent = this.value;
		var elemento = this.parentNode;
		elemento.appendChild(createInput("hidden", "postIts[" + numero + "].conteudo", "texto"));
		elemento.appendChild(createInput("hidden", "postIts[" + numero + "].posicaoHorizontal", elemento.style.left));
		elemento.appendChild(createInput("hidden", "postIts[" + numero + "].posicaoVertical", elemento.style.top	));
		if(elemento.classList.contains("postIt")) {
			elemento.appendChild(createInput("hidden", "postIts[" + numero + "].cor", "corFCF0AD"));
		}
		elemento.classList.remove("semConteudo");
		elemento.removeChild(this);
		elemento.appendChild(linkRemove);
		elemento.appendChild(texto);
	}	
}


var SHIFT = false; 
document.body.addEventListener('keyup', function(event){ console.log(event); 
	//Shift+ENTER 
	if(event.keyCode == 13 && SHIFT) { 
		console.log('HAU'); 
	} 
	//Desligar Shift 
	if (event.keyCode == 16) { 
		SHIFT = false; 
	} 
}); 
document.body.addEventListener('keydown', function(event) { 
	if (event.keyCode == 16 && !SHIFT) { 
		SHIFT = true; 
	}; 
}); 

function createInput(type, name, value) {
	var input = document.createElement('input');
	input.type = type;
	input.name = name;
	input.value = value;
	return input;
}


function createTextarea(name, limiteCaracteres) {
	var textarea = document.createElement("textarea");
	textarea.name = name;
	if(limiteCaracteres > 0) {
		textarea.maxLength = limiteCaracteres;
	}
	return textarea;
}

function removeElemento() {
	this.parentNode.remove();
}