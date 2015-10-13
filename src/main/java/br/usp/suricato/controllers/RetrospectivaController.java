package br.usp.suricato.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.usp.suricato.daos.LousaDao;
import br.usp.suricato.daos.RetrospectivaDao;
import br.usp.suricato.daos.UsuarioDao;
import br.usp.suricato.models.Lousa;
import br.usp.suricato.models.Retrospectiva;

@Controller
@Transactional
@RequestMapping("/retrospectiva")
public class RetrospectivaController {

	@Autowired
	private RetrospectivaDao retrospectivaDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private LousaDao lousaDao;

	@RequestMapping("/mostra")
	public String mostraRetrospectiva(Integer id, Model model) {
		model.addAttribute("retrospectiva", retrospectivaDao.load(id));
		return "retrospectiva/mostra";
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public String salvaRetrospectiva(@ModelAttribute("retrospectiva") Retrospectiva retrospectiva, Model model) {
		retrospectivaDao.atualiza(retrospectiva);
		return "redirect:mostra?id=" + retrospectiva.getId();
	}
	
	@RequestMapping("/cria")
	public String criaRestrospectiva(Retrospectiva retrospectiva, Model model) {
		retrospectiva.setCriador(usuarioDao.buscaPorNome(retrospectiva.getCriador().getNome()));
		retrospectiva.setLousa(lousaDao.load(retrospectiva.getLousa().getId()));
		retrospectivaDao.salva(retrospectiva);
		return "redirect:mostra?id=" + retrospectiva.getId();
	}
	
	@RequestMapping("/nova")
	public String novaRetrospectiva(Model model) {
		List<Lousa> lousas = lousaDao.lista();
		model.addAttribute("lousas", lousas);
		return "retrospectiva/nova";
	}
	
}
