package br.usp.suricato.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usp.suricato.models.Lousa;

@Controller
@RequestMapping("/lousa")
public class LousaController {

	@RequestMapping("/nova")
	public String novaLousa(Lousa lousa, Model model) {
		model.addAttribute("lousa", lousa);
		return "lousa/mostra";
	}
	
}
