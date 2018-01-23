;(function () {
	const $infosRetros = document.querySelector(".infos-retrospectiva")
	const $listaRetros = document.querySelector(".lista-retrospectivas")
	$infosRetros.addEventListener("click", function(event) {
		const $target = event.target
		if($target.classList.contains('infos-retrospectiva_disposicao')) {
			if($target.classList.contains("--minimized")) {
				$listaRetros.classList.add("--minimized")
				$listaRetros.classList.remove("--maximized")
				document.querySelector(".infos-retrospectiva_disposicao.--maximized").removeAttribute("data-selected")
				document.querySelector(".infos-retrospectiva_disposicao.--minimized").setAttribute("data-selected", "")
			} else if ($target.classList.contains("--maximized")) {
				$listaRetros.classList.remove("--minimized")
				$listaRetros.classList.add("--maximized")
				document.querySelector(".infos-retrospectiva_disposicao.--maximized").setAttribute("data-selected", "")
				document.querySelector(".infos-retrospectiva_disposicao.--minimized").removeAttribute("data-selected")
			}
		}
	})
	
})()