const inputFile = document.querySelector(".cadastro-foto_campo")
inputFile.addEventListener("change", function(event) {
	const $input = event.target
	if ($input.files && $input.files[0]) {
		let reader = new FileReader();
		
		reader.onload = function (e) {
			const campoImagem = document.querySelector(".cadastro-foto_imagem")
			campoImagem.src = e.target.result; 
		};
		
		reader.readAsDataURL($input.files[0]);
	}
})