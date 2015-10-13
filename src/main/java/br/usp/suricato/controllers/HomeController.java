package br.usp.suricato.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usp.suricato.daos.RetrospectivaDao;
import br.usp.suricato.models.Retrospectiva;

@Controller
public class HomeController {

	@Autowired
	private RetrospectivaDao retrospectivaDao;
	
	@RequestMapping(value={"/", "/index"})
	public String index(Model model, Principal principal) {
		List<Retrospectiva> retrospectivas = retrospectivaDao.listaRetrospectivasDoUsuario(principal.getName());
		model.addAttribute("retrospectivas", retrospectivas);
		return "index";
	}
	
}
