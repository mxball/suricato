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
		var postIt = this.parentNode;
		postIt.classList.remove("semConteudo");
		postIt.removeChild(this);
		postIt.appendChild(linkRemove);
		postIt.appendChild(texto);
	}	
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