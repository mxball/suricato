$(".novoPostIt").draggable({
	helper: "clone"
});

$("#atividade").droppable({
	accept: ".novoPostIt",
    drop: function (event, ui) {
    	var postIt = $(ui.draggable).clone();
    	postIt.removeClass("novoPostIt");
    	postIt.addClass("postIt");
    	var cursorX = (event.clientX * 100.0) / window.innerWidth;
    	var cursorY = (event.clientY * 100.0) / window.innerHeight;
    	postIt.css('left', cursorX + "%");
    	postIt.css('top', cursorY + "%");
    	var comentario = createTextarea("comentario");
    	comentario.addEventListener('keydown', teclado); 
    	postIt.append(comentario);
        postIt.appendTo($(this).parent());
    }
});


function teclado(event) {
	if (event.keyCode == 13) {
		var texto = document.createElement('p');
		texto.classList.add("comentario");
		texto.textContent = this.value;
		var postIt = this.parentNode;
		postIt.removeChild(this);
		postIt.appendChild(texto);
	}	
}

function createInput(type, name, id, value) {
	var input = document.createElement('input');
	input.type = type;
	input.name = name;
	input.id = id;
	input.value = value;
	return input;
}