function atualizaInput() {
	let campoIntegrantes = document.querySelector(".--lista-integrantes").getBoundingClientRect();
	let integrantesCadastrados = document.querySelectorAll(".integrante");
	let ultimoIntegrante = integrantesCadastrados[integrantesCadastrados.length - 1].getBoundingClientRect();
	const tamanhoTotal = campoIntegrantes.right - campoIntegrantes.left;
	const tamanhoIntegranteCadastrados = ultimoIntegrante.right - campoIntegrantes.left;
	const novoTamanhoInput = tamanhoTotal - tamanhoIntegranteCadastrados;
	
	const inputNovoIntegrante = document.querySelector(".integrante-adiciona");
	if(novoTamanhoInput > 170) {	
		inputNovoIntegrante.style.width = (novoTamanhoInput - 30) + "px";
	} else {
		inputNovoIntegrante.style.width = (tamanhoTotal - 20) + "px";
	}
}

atualizaInput();


let quantidadeIntegrantes = document.querySelectorAll(".integrante").length;
let cores = ["#6de069", "#eb5fea", "#68e1ff", "#ff6885", "#ffb647", "#699ce0"];

$(".integrante-adiciona").autocomplete({
	minLength: 3,
    source: function (request, response) {
        $.getJSON("/usuario/busca", request, function(result) {
            response($.map(result, function(usuario) {
            	return {
                    label: usuario.nome,
                    value: usuario.id
                }
            }));
        });
    },
    select : function(event, ui) {
        if (ui.item) {
            event.preventDefault();
            console.log(ui.item.label);
            console.log(ui.item.value);
            const inputNome = criaInputNome(quantidadeIntegrantes, ui.item.label);
            document.querySelector(".cadastro").appendChild(inputNome);
            const inputId = criaInputId(quantidadeIntegrantes, ui.item.value);
            document.querySelector(".cadastro").appendChild(inputId);
            const spanUsuario = criaSpan(quantidadeIntegrantes, ui.item.label);
            document.querySelector(".--lista-integrantes").insertBefore(spanUsuario, document.querySelector(".integrante-adiciona"));
            quantidadeIntegrantes++;
            document.querySelector(".integrante-adiciona").value = "";
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
	let span = document.createElement("span");
	span.className = "integrante";
	span.dataset.id = id;
	span.textContent = value;
	span.style.backgroundColor = cores[quantidadeIntegrantes % cores.length];
	span.style.marginRight = "4px";
	return span;
}