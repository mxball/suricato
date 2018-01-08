let listaIntegrantes = document.querySelector(".--lista-integrantes");
let integrantesCadastrados = document.querySelectorAll(".integrante");
let quantidadeIntegrantes = integrantesCadastrados.length;
const inputNovoIntegrante = document.querySelector(".integrante-adiciona");

function atualizaInput() {
	let campoIntegrantes = listaIntegrantes.getBoundingClientRect();
	let integrantesCadastrados = document.querySelectorAll(".integrante");
	let ultimoIntegrante = integrantesCadastrados[integrantesCadastrados.length - 1].getBoundingClientRect();
	const tamanhoTotal = campoIntegrantes.right - campoIntegrantes.left;
	const tamanhoIntegranteCadastrados = ultimoIntegrante.right - campoIntegrantes.left;
	const novoTamanhoInput = tamanhoTotal - tamanhoIntegranteCadastrados;
	
	if(novoTamanhoInput > 170) {	
		inputNovoIntegrante.style.width = (novoTamanhoInput - 30) + "px";
	} else {
		inputNovoIntegrante.style.width = (tamanhoTotal - 20) + "px";
	}
}
atualizaInput();

$(".integrante-adiciona").autocomplete({
	minLength: 3,
    source: function (request, response) {
        $.getJSON("/usuario/busca", request, function(result) {
        	let listaUsuario = $.map(result, (usuario) => {
        							return {
        								label: usuario.nome,
        								value: usuario.id
        							}
            					});
        	let listaIds = $.map( document.querySelectorAll(".cadastro-campo_id"), (integrante) => { return parseInt(integrante.value) } );
        	console.log(listaIds);
        	listaUsuario = listaUsuario.filter(usuario => { return listaIds.indexOf(usuario.value) == -1 });
            response(listaUsuario);
        });
    },
    select : function(event, ui) {
        if (ui.item) {
            event.preventDefault();
            const inputNome = criaInputNome(quantidadeIntegrantes, ui.item.label);
            document.querySelector(".cadastro").appendChild(inputNome);
            const inputId = criaInputId(quantidadeIntegrantes, ui.item.value);
            document.querySelector(".cadastro").appendChild(inputId);
            const spanUsuario = criaSpan(quantidadeIntegrantes, ui.item.label);
            listaIntegrantes.insertBefore(spanUsuario, inputNovoIntegrante);
            integrantesCadastrados = document.querySelectorAll(".integrante");
            quantidadeIntegrantes++;
            inputNovoIntegrante.value = "";
            atualizaInput();
            return false;
        }
    }
});

function criaInputId(id, value) {
	let inputId = document.createElement("input");
	inputId.type = "hidden";
	inputId.name = "integrantes[" + id + "].id";
	inputId.id = "integrante_id_"+id;
	inputId.value = value;
	inputId.className = "cadastro-campo_id";
	return inputId;
}

function criaInputNome(id, value) {
	let inputId = document.createElement("input");
	inputId.type = "hidden";
	inputId.name = "integrantes[" + id + "].nome";
	inputId.id = "integrante_nome_"+id;
	inputId.value = value;
	return inputId;
}

function criaSpan(id, value) {
	const cores = ["#6de069", "#eb5fea", "#68e1ff", "#ff6885", "#ffb647", "#699ce0"];
	let span = document.createElement("span");
	span.className = "integrante";
	span.textContent = value;
	span.style.backgroundColor = cores[quantidadeIntegrantes % cores.length];
	span.style.marginRight = "4px";
	let remove = document.createElement("a");
	remove.dataset.usuarioId = id;
	remove.className = "integrante-remove glyphicon glyphicon-remove";
	span.appendChild(remove);
	return span;
}

listaIntegrantes.onclick = (event) => {
	if(event.target.classList.contains("integrante-remove")) {
		const id = event.target.dataset.usuarioId;
		document.querySelector("#integrante_nome_" + id).remove();
		document.querySelector("#integrante_id_" + id).remove();
		event.target.parentNode.remove();
		atualizaInput();
	}
}