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

function createLike(numeroLikes) {
	var like = createLink("like", adicionaLike);
	like.onclick = adicionaLike;
	like.classList.add("default");
	like.textContent=numeroLikes;
	return like;
}

function createDeslike(numeroDeslikes) {
	var deslike = createLink("dislike", adicionaDeslike);
	deslike.onclick = adicionaDeslike;
	deslike.classList.add("default");
	deslike.textContent=numeroDeslikes;
	return deslike;
}

function adicionaLike() {
	var elemento = $(this).parent();
	var conteudo = new Conteudo(elemento, "adicionaLike");
	conteudo.setId(elemento.attr("id").split('_')[1]);
	conteudo.adicionaNumeroVotos();
	enviaMensagem(conteudo);
}

function adicionaDeslike() {
	var elemento = $(this).parent();
	var conteudo = new Conteudo(elemento, "adicionaDeslike");
	conteudo.setId(elemento.attr("id").split('_')[1]);
	conteudo.adicionaDeslike();
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
	this.numeroVotos = parseInt(elemento.find(".like").text());
	this.numeroDeslikes = parseInt(elemento.find(".dislike").text());
	
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
	
	this.adicionaDeslike = function() {
		this.numeroDeslikes++;
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
			'numeroVotos'       : this.numeroVotos,
			'numeroDeslikes'    : this.numeroDeslikes
		};
	}
	
}

var retrospectivaId = lousa.dataset.retrospectivaId;
var usuarioNome = lousa.dataset.usuarioNome;

var stompClient = null;
var socket = new SockJS('/retrospective-websocket');
stompClient = Stomp.over(socket);
stompClient.debug = null;
stompClient.connect({}, function (frame) {
	stompClient.subscribe('/message/retrospectiva/asndjkahsdjhds/' + retrospectivaId, function (conteudoJson) {
		var json = JSON.parse(conteudoJson.body);
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
				elemento.appendChild(createLike(json.numeroVotos));
				elemento.appendChild(createDeslike(json.numeroDeslikes));
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
			
			lousa.appendChild(elemento);
			elemento.click(getAcao);
		} else if(json.operacao == "remove") {
			$("#lousa #" + json.tipoConteudo + "_" + json.id).remove();
		} else if(json.operacao == "adicionaLike") {
			$("#lousa #" + json.tipoConteudo + "_" + json.id + " .like").text(json.numeroVotos);
		} else if(json.operacao == "adicionaDeslike") {
			$("#lousa #" + json.tipoConteudo + "_" + json.id + " .dislike").text(json.numeroDeslikes);			
		}
	});
});

function enviaMensagem(conteudo) {
	stompClient.send("/app/retrospectiva/asndjkahsdjhds/" + retrospectivaId + "/" + usuarioNome, {}, JSON.stringify(conteudo.getJson()));
}