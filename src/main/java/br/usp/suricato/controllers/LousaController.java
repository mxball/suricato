package br.usp.suricato.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lousa")
public class LousaController {

	@RequestMapping("/nova")
	public String novaLousa() {
		return "lousa/gladSad";
	}
	
}
