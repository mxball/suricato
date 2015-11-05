var lousa = document.querySelector("#lousa");
window.onload = getAcao;
lousa.addEventListener("click", getAcao);

function getAcao() {
	$(".comConteudo").draggable({
		stop: function (event, ui) {
			var conteudo = new Conteudo($(ui.helper), "atualiza");
			conteudo.setId(ui.helper.attr("id").split('_')[1]);
			conteudo.setTexto(ui.helper.find("p").html());
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
		comentario.addEventListener('keydown', tecladoKeyDown);
		draggable.append(comentario);
		draggable.appendTo($(this).parent());
		comentario.focus();
    }
});

var SHIFT = false; 

function tecladoKeyDown(event) {
	if (event.keyCode == 13 && !SHIFT) {
		var elemento = this.parentNode;
		var conteudo = new Conteudo($(elemento), "adiciona");
		conteudo.setTexto(this.value.replace(/\n/g, '<br/>'));
		enviaMensagem(conteudo);
		elemento.style.display = 'none';
	} 
}

document.body.addEventListener("keydown", function(event) {
	if(event.keyCode == 16){
		SHIFT = true;
	}
});

document.body.addEventListener("keyup", function(event) {
	if(event.keyCode == 16){
		SHIFT = false;
	}
});

function createConteudo(texto) {
	var conteudo = document.createElement('p');
	conteudo.classList.add("conteudo");
	conteudo.innerHTML = texto;
	return conteudo;
}

function createLink(classe, click) {
	var link = document.createElement('a');
	link.href = "#";
	link.onclick = click;
	link.classList.add(classe);
	return link;
}

function createLinkRemove() {
	return createLink("remover", removeElemento);
}

function createLinkEdita() {
	return createLink("editar", editaElemento);
}

function createLike() {
	var like = createLink("like", adicionaDot);
	like.onclick = adicionaDot;
	like.classList.add("default");
	return like;
}

function createDislike() {
	var dislike = createLink("dislike", removeDot);
	dislike.onclick = removeDot;
	dislike.classList.add("default");
	return dislike;
}

function createDotVotes() {
	var dotVotes = document.createElement('div');
	dotVotes.classList.add("dotVotes");
	return dotVotes;
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
		if (event.keyCode == 13 && !SHIFT) {
			comentario.disabled = true;
			var conteudo = new Conteudo(elemento, "atualiza");
			conteudo.setId(id);
			conteudo.setTexto(comentario.value.replace(/\n/g, '<br/>'));
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
	comentario.value = conteudo.html().replace(/\<br\>/g, "\n");
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

var retrospectivaId = lousa.dataset.retrospectivaId;
var usuarioNome = lousa.dataset.usuarioNome;
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
				elemento.appendChild(createLike());
				elemento.appendChild(createDislike());
			} else if(json.tipoConteudo == "comentario")  {
				elemento.style.width = json.largura + "px";
				elemento.style.minHeight = json.altura + "px";
				elemento.id = "comentario_" + json.id;
			}
			elemento.style.left = json.posicaoHorizontal + "%";
			elemento.style.top = json.posicaoVertical + "%";
			elemento.appendChild(createLinkRemove());
			elemento.appendChild(createLinkEdita());
			var conteudo = createConteudo(json.texto);
			elemento.appendChild(conteudo);
			elemento.appendChild(createDotVotes());
			lousa.appendChild(elemento);
			elemento.click(getAcao);
		} else if(json.operacao == "remove") {
			$("#lousa #" + json.tipoConteudo + "_" + json.id).remove();
		}
	}
}