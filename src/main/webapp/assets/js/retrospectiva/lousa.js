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
			var conteudo = new Conteudo($(ui.helper), "atualiza");
			conteudo.setId(ui.helper.attr("id").split('_')[1]);
			conteudo.setTexto(ui.helper.find("p").text());
			enviaMensagem(conteudo);
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
		var conteudo = new Conteudo($(elemento), "adiciona");
		conteudo.setTexto(this.value);
		enviaMensagem(conteudo);
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

function createLinkEdita() {
	var linkEdita = document.createElement('a');
	linkEdita.onclick = editaElemento;
	linkEdita.href = "#";
	linkEdita.classList.add("editar");
	return linkEdita;
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
document.body.addEventListener('keyup', function(event){ 
//	console.log(event); 
	//Shift+ENTER 
	if(event.keyCode == 13 && SHIFT) { 
//		console.log('HAU'); 
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
	textarea.value = "";
	return textarea;
}

$(".remover").click(removeElemento);

function removeElemento() {
	var elemento = $(this.parentNode);
	var conteudo = new Conteudo(elemento, "remove");
	conteudo.setId(elemento.attr("id").split('_')[1]);
	enviaMensagem(conteudo);
}

$(".editar").click(editaElemento);

function editaElemento() {
	var elemento = $(this.parentNode);
	var limiteCaracteres = elemento.hasClass("postIt") ? 100 : 0;
	var comentario = createTextarea("conteudo", limiteCaracteres);
	var id = elemento.attr("id").split('_')[1];
	var tipoElemento = elemento.attr("id").split('_')[0];
	comentario.addEventListener('keydown', function(event) {
		if (event.keyCode == 13) {
			comentario.disabled = true;
			var conteudo = new Conteudo(elemento, "atualiza");
			conteudo.setId(id);
			conteudo.setTexto(comentario.value);
			if(tipoElemento == "comentario") {
				conteudo.setLargura(comentario.style.width.replace("px", ""));
				conteudo.setAltura(comentario.style.height.replace("px", ""));
			}
			enviaMensagem(conteudo);
		}
	});
	var conteudo = $(elemento).find(".conteudo");
	if(tipoElemento == "comentario") {
		comentario.style.width = elemento.width() + "px";
		comentario.style.height = elemento.height() + "px";
	}
	comentario.value = conteudo.text();
	conteudo.remove();
	elemento.addClass("semConteudo");
	elemento.removeClass("comConteudo");
	$(elemento).find(".editar").remove();
	$(elemento).find(".remover").remove();
	elemento.append(comentario);
	comentario.focus();	
}

function Conteudo(elemento, operacao) {
	this.tipoConteudo = elemento.hasClass("postIt") ? "postIt" : "comentario";
	this.operacao = operacao;
	this.id = 0;
	this.texto = "";
	this.posicaoHorizontal = (elemento.offset().left * 100.0) / window.innerWidth;
	this.posicaoVertical = (elemento.offset().top* 100.0) / window.innerHeight;
	this.largura = elemento.width();
	this.altura = elemento.height(); 
	this.cor = elemento.hasClass("postIt") ? elemento.attr("class").match(/\bcor[^\s]+\b/)[0] : "";
	
	this.setId = function(id) {
		this.id = parseInt(id);
	}
	
	this.setTexto = function(texto) {
		this.texto = texto;
	}
	
	this.setLargura = function(largura) {
		this.largura = parseInt(largura);
	}
	
	this.setAltura = function(altura) {
		this.altura = parseInt(altura);
	}
	
	this.getJson = function() {
		return {
			'tipoConteudo'		: this.tipoConteudo,
			'operacao'          : this.operacao,
			'id'                : this.id,
			'texto'             : this.texto,
			'posicaoHorizontal' : this.posicaoHorizontal,
			'posicaoVertical'   : this.posicaoVertical,
			'largura'           : this.largura,
			'altura'            : this.altura,
			'cor'               : this.cor
		};
	}
	
}

var retrospectiva = document.querySelector("#retrospectiva-id");
var retrospectivaId = retrospectiva.value;
var usuarioNome = retrospectiva.dataset.usuarioNome;
var connection = new WebSocket('ws://localhost:8080/suricato/retrospectiva/asndjkahsdjhds/' + retrospectivaId + '/' + usuarioNome);
function enviaMensagem(conteudo) {
	connection.send(JSON.stringify(conteudo.getJson()));
}
connection.onerror = function(error) {
	console.log('WebSocket Error ' + error);
}
connection.onmessage = function(mensagemServidor) {
	var mensagem = mensagemServidor.data.toString();
	if(mensagem != "Ping") {
		var json = JSON.parse(mensagemServidor.data);
		if(json.operacao == "adiciona" || json.operacao == "atualiza") {
			if(json.operacao == "atualiza") {
				$("#lousa #" + json.tipoConteudo + "_" + json.id).remove();
			}
			var elemento = document.createElement("div");
			elemento.classList.add(json.tipoConteudo);
			elemento.classList.add("comConteudo");
			if(json.tipoConteudo == "postIt") {
				elemento.classList.add(json.cor);
				elemento.id = "postIt_" + json.id;
				adicionaPostIt(elemento, json.texto, json.posicaoHorizontal, json.posicaoVertical, json.cor, json.id);
			} else if(json.tipoConteudo == "comentario")  {
				elemento.style.width = json.largura + "px";
				elemento.style.minHeight = json.altura + "px";
				elemento.id = "comentario_" + json.id;
				adicionaComentario(elemento, json.texto, json.posicaoHorizontal, json.posicaoVertical, json.largura, json.altura, json.id);
			}
			elemento.style.left = json.posicaoHorizontal + "%";
			elemento.style.top = json.posicaoVertical + "%";
			elemento.appendChild(createLinkRemove());
			elemento.appendChild(createLinkEdita());
			var conteudo = createConteudo(json.texto);
			elemento.appendChild(conteudo);
			lousa.appendChild(elemento);
			elemento.click(getAcao);
			console.log(elemento)
		} else if(json.operacao == "remove") {
			$("#lousa #" + json.tipoConteudo + "_" + json.id).remove();
		}
	}
}