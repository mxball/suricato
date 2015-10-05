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
    	var postIt = $(ui.draggable).clone();
    	postIt.removeClass("draggable");
    	postIt.children().remove();
    	var cursorX = (event.clientX * 100.0) / window.innerWidth;
    	var cursorY = (event.clientY * 100.0) / window.innerHeight;
    	postIt.css('left', cursorX + "%");
    	postIt.css('top', cursorY + "%");
    	var comentario = createTextarea("conteudo");
    	comentario.addEventListener('keydown', teclado); 
    	postIt.append(comentario);
        postIt.appendTo($(this).parent());
    }
});

function teclado(event) {
	if (event.keyCode == 13) {
		var texto = document.createElement('p');
		texto.classList.add("conteudo");
		texto.textContent = this.value;
		var postIt = this.parentNode;
		postIt.classList.remove("semConteudo");
		postIt.removeChild(this);
		postIt.appendChild(texto);
	}	
}

function createTextarea(name) {
	var textarea = document.createElement("textarea");
	textarea.name = name;
	textarea.maxLength = 100;
	return textarea;
}