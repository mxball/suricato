package br.usp.suricato.controllers;

import java.security.Principal;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usp.suricato.daos.TimeDao;
import br.usp.suricato.daos.UsuarioDao;
import br.usp.suricato.models.Time;

@Controller
@RequestMapping("/time")
@Transactional
public class TimeController {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private TimeDao timeDao;
	
	@RequestMapping("/novo")
	public String novo(Model model, Principal principal) {
		model.addAttribute("usuario", usuarioDao.buscaPorNome(principal.getName()));
		return "time/novo";
	}
	
	@RequestMapping("/criar")
	public String cadastrar(@Valid @ModelAttribute("time") Time time, Model model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("usuario", time.getIntegrantes().get(0));
			return "time/novo"; 
		}
		timeDao.save(time);
		return "redirect:/index";
	}
	
}
