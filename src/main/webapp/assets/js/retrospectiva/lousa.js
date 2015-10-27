var lousa = document.querySelector("#lousa");
var numeroPostIts = lousa.dataset.postitQuantidade;
var numeroComentarios = lousa.dataset.comentarioQuantidade;

window.onload = getAcao;
lousa.addEventListener("click", getAcao);

function getAcao() {
	$(".comConteudo").draggable({
		stop: function (event, ui) {
			var posicaoEsquerda = (ui.offset.left * 100.0) / window.innerWidth;
			var posicaoTopo = (ui.offset.top * 100.0) / window.innerHeight;
			this.querySelector("[name$='posicaoHorizontal']").value = posicaoEsquerda;
			this.querySelector("[name$='posicaoVertical']").value = posicaoTopo; 
		}
	});
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
		comentario.focus();
    }
});

function teclado(event) {
	if (event.keyCode == 13) {
		var elemento = this.parentNode;
		var posicaoHorizontal = elemento.style.left.replace("%", "");
		var posicaoVertical = elemento.style.top.replace("%", "");
		var texto = this.value;
		var largura = this.clientWidth;
		var altura = this.clientHeight;
		if(elemento.classList.contains("postIt")) {
			adicionaPostIt(elemento, texto, posicaoHorizontal, posicaoVertical);
		} else if(elemento.classList.contains("comentario")) {
			adicionaComentario(elemento, texto, posicaoHorizontal, posicaoVertical, largura, altura);
		}
		elemento.classList.remove("semConteudo");
		elemento.classList.add("comConteudo");
		elemento.removeChild(this);
		elemento.appendChild(createLinkRemove());
		var conteudo = createConteudo(texto);
		conteudo.style.width = largura + "px";
		conteudo.style.minHeight = altura + "px";
		elemento.appendChild(conteudo);
		elemento.click(getAcao);
	}	
}

function createConteudo(texto) {
	var conteudo = document.createElement('p');
	conteudo.classList.add("conteudo");
	conteudo.textContent = texto;
	return conteudo;
}

function createLinkRemove() {
	var linkRemove = document.createElement('a');
	linkRemove.onclick = removeElemento;
	linkRemove.href = "#";
	linkRemove.classList.add("remover");
	return linkRemove;
}

function adicionaComentario(elemento, texto, posicaoHorizontal, posicaoVertical, largura, altura){
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].conteudo", texto));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].posicaoHorizontal", posicaoHorizontal));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].posicaoVertical", posicaoVertical));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].largura", largura));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].altura", altura));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].excluir", false));
	numeroComentarios++;
}

function adicionaPostIt(elemento, texto, posicaoHorizontal, posicaoVertical) {
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].conteudo", texto));
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].posicaoHorizontal", posicaoHorizontal));
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].posicaoVertical", posicaoVertical));
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].cor", elemento.className.match(/\bcor[^\s]+\b/)));
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].excluir", false));
	numeroPostIts++;
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

$(".remover").click(removeElemento);

function removeElemento() {
	$(this.parentNode).css('display', 'none');
	$(this.parentNode).find("input[name$='excluir']").val('true');
}