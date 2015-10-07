package br.usp.suricato.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usp.suricato.daos.LousaDao;
import br.usp.suricato.models.Lousa;

@Controller
public class HomeController {

	private LousaDao lousaDao;

	@Autowired
	public HomeController(LousaDao lousaDao) {
		this.lousaDao = lousaDao;
	}

	@RequestMapping(value={"/", "index"})
	public String index(Model model) {
		List<Lousa> lousas = lousaDao.lista();
		model.addAttribute("lousas", lousas);
		return "index";
	}
	
}
