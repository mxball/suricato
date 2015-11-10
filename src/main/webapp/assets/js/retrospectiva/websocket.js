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

function createDotVote() {
	var dotVote = document.createElement("div");
	dotVote.classList.add("dotVote");
	return dotVote;
}

function adicionaDot() {
	var elemento = $(this).parent();
	var conteudo = new Conteudo(elemento, "adicionaDotVote");
	conteudo.setId(elemento.attr("id").split('_')[1]);
	conteudo.adicionaNumeroVotos();
	enviaMensagem(conteudo);
}

function removeDot() {
	var elemento = $(this).parent();
	var conteudo = new Conteudo(elemento, "removeDotVote");
	conteudo.setId(elemento.attr("id").split('_')[1]);
	conteudo.removeNumeroVotos();
	enviaMensagem(conteudo);
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
	this.numeroVotos = elemento.find(".dotVotes .dotVote").length;
	
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
	
	this.adicionaNumeroVotos = function() {
		this.numeroVotos++;
	}
	
	this.removeNumeroVotos = function() {
		if(this.numeroVotos > 0) {
			this.numeroVotos--;
		}
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
			'cor'               : this.cor,
			'numeroVotos'       : this.numeroVotos
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
			var dotVotes = createDotVotes();
			for (var i = 0; i < json.numeroVotos; i++) {
				dotVotes.appendChild(createDotVote());
			}
			elemento.appendChild(dotVotes);
			lousa.appendChild(elemento);
			elemento.click(getAcao);
		} else if(json.operacao == "remove") {
			$("#lousa #" + json.tipoConteudo + "_" + json.id).remove();
		} else if(json.operacao == "adicionaDotVote") {
			$("#lousa #" + json.tipoConteudo + "_" + json.id + " .dotVotes").append(createDotVote());
		} else if(json.operacao == "removeDotVote") {
			$("#lousa #" + json.tipoConteudo + "_" + json.id + " .dotVotes .dotVote").first().remove();			
		}
	}
}