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
			enviaMensagem("postIt|adiciona|" + texto + "|" + posicaoHorizontal + "|" + posicaoVertical + "|" + elemento.className.match(/\bcor[^\s]+\b/));
		} else if(elemento.classList.contains("comentario")) {
			enviaMensagem("comentario|adiciona|" + texto + "|" + posicaoHorizontal + "|" + posicaoVertical + "|" + largura + "|" + altura);
		}
		elemento.style.display = 'none';
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

function adicionaComentario(elemento, texto, posicaoHorizontal, posicaoVertical, largura, altura, id){
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].conteudo", texto));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].posicaoHorizontal", posicaoHorizontal));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].posicaoVertical", posicaoVertical));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].largura", largura));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].altura", altura));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].excluir", false));
	elemento.appendChild(createInput("hidden", "comentarios[" + numeroComentarios + "].id", id));
	numeroComentarios++;
}

function adicionaPostIt(elemento, texto, posicaoHorizontal, posicaoVertical, cor, id) {
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].conteudo", texto));
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].posicaoHorizontal", posicaoHorizontal));
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].posicaoVertical", posicaoVertical));
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].cor", cor));
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].excluir", false));
	elemento.appendChild(createInput("hidden", "postIts[" + numeroPostIts + "].id", id));
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
	var elemento = $(this.parentNode);
	var id = elemento.attr("id").split('_')[1];
	var tipoElemento = elemento.attr("id").split('_')[0];
	enviaMensagem(tipoElemento + "|remove|" + id);
}


var retrospectiva = document.querySelector("#retrospectiva-id");
var retrospectivaId = retrospectiva.value;
var usuarioNome = retrospectiva.dataset.usuarioNome;
var connection = new WebSocket('ws://localhost:8080/suricato/retroespectiva/asndjkahsdjhds/' + retrospectivaId + '/' + usuarioNome);
function enviaMensagem(mensagem) {
	console.log(mensagem);
	connection.send(mensagem);
}
connection.onerror = function(error) {
	console.log('WebSocket Error ' + error);
}
connection.onmessage = function(mensagemServidor) {
	var mensagem = mensagemServidor.data.toString()
	console.log(mensagemServidor.data.toString());
	if(mensagem != "Ping") {
		var argumentos = mensagem.split("|");
		var tipoElemento = argumentos[0];
		var operacao = argumentos[1];
		if(operacao == "remove") {
			var id = argumentos[2];
			$("#lousa #" + tipoElemento + "_" + id).remove();
		} else {
			var elemento = document.createElement("div");
			elemento.classList.add(tipoElemento);
			elemento.classList.add("comConteudo");
			var texto = argumentos[2];
			var posicaoHorizontal = argumentos[3];
			var posicaoVertical = argumentos[4];
			if(tipoElemento == "postIt") {
				var cor = argumentos[5];
				var id = argumentos[6];
				elemento.classList.add(cor);
				elemento.id = "postIt_" + id;
				adicionaPostIt(elemento, texto, posicaoHorizontal, posicaoVertical, cor, id);
			} else if(tipoElemento == "comentario")  {
				var largura = argumentos[5];
				var altura = argumentos[6];
				var id = argumentos[7];
				elemento.style.width = largura + "px";
				elemento.style.minHeight = altura + "px";
				elemento.id = "comentario_" + id;
				adicionaComentario(elemento, texto, posicaoHorizontal, posicaoVertical, largura, altura, id);
			}
			elemento.style.left = posicaoHorizontal + "%";
			elemento.style.top = posicaoVertical + "%";
			elemento.appendChild(createLinkRemove());
			var conteudo = createConteudo(texto);
			elemento.appendChild(conteudo);
			elemento.click(getAcao);
			lousa.appendChild(elemento);
		}
	}
	
}