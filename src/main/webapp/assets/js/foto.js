const inputFile = document.querySelector(".foto-arquivo")
inputFile.addEventListener("change", function(event) {
	const $input = event.target
	if ($input.files && $input.files[0]) {
		let reader = new FileReader();
		
		reader.onload = function (e) {
			const campoImagem = document.querySelector(".foto-visualiza")
			campoImagem.src = e.target.result; 
		};
		
		reader.readAsDataURL($input.files[0]);
		if($input.classList.contains("--trocaNomeArquivo")) {
			const labelImagem = document.querySelector(".foto-texto")
			labelImagem.textContent = $input.files[0].name
		}
	}
});