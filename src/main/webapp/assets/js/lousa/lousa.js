document.querySelector("#lousa").addEventListener("click", getAcao);

function getAcao(event) {
	if(event.target.id == 'atividade'){
		if(document.querySelector("#adiciona-postit").checked) {
			getPostIt(event);
		} else {
			getTexto(event);
		}
	} else {
		movePostIt();
	}
}

function getTexto(coordenadas) {
	console.log("Teste " + coordenadas);
	var cursorX = (coordenadas.clientX * 100.0) / window.innerWidth;
	var cursorY = (coordenadas.clientY * 100.0) / window.innerHeight;
	var text = createTextarea("comments");

	text.style.left = cursorX + "%";
	text.style.top = cursorY + "%";
	text.classList.add("texto");

	document.querySelector("#lousa").appendChild(text);
}

function createTextarea(name) {
	var textarea = document.createElement("textarea");
	textarea.rows = 3;
	textarea.cols = 45;
	textarea.name = name;
	textarea.maxLength = 100;
	return textarea;
}

function movePostIt() {
	$(".postIt").draggable();
}
