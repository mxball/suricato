package br.usp.suricato.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usp.suricato.daos.RetrospectivaDao;
import br.usp.suricato.daos.UsuarioDao;
import br.usp.suricato.models.Retrospectiva;

@Controller
public class HomeController {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private RetrospectivaDao retrospectivaDao;
	
	@RequestMapping(value={"/", "/index"})
	public String index(Model model, Principal principal) {
		String nomeDoUsuario = principal.getName();
		List<Retrospectiva> retrospectivasAbertas = retrospectivaDao.listaRetrospectivasAbertasDoUsuario(nomeDoUsuario);
		List<Retrospectiva> retrospectivasEncerradas = retrospectivaDao.listaRetrospectivasEncerradasDoUsuario(nomeDoUsuario);
		model.addAttribute("retrospectivasAbertas", retrospectivasAbertas);
		model.addAttribute("retrospectivasEncerradas", retrospectivasEncerradas);
		model.addAttribute("usuario", usuarioDao.buscaPorNome(nomeDoUsuario));
		return "index";
	}
	
}
