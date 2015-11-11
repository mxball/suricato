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

$(".remover").click(removeElemento);
$(".editar").click(editaElemento);
$(".like").on("click", adicionaDot);
$(".dislike").on("click", removeDot);

function removeElemento() {
	var elemento = $(this.parentNode);
	var conteudo = new Conteudo(elemento, "remove");
	conteudo.setId(elemento.attr("id").split('_')[1]);
	enviaMensagem(conteudo);
}

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
	$(elemento).find(".like").remove();
	$(elemento).find(".dislike").remove();
	elemento.append(comentario);
	comentario.focus();	
}