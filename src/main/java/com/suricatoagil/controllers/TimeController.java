package com.suricatoagil.controllers;

import java.security.Principal;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suricatoagil.daos.TimeDao;
import com.suricatoagil.daos.UsuarioDao;
import com.suricatoagil.models.Time;
import com.suricatoagil.models.Usuario;

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
	
	@RequestMapping(value="/criar", method=RequestMethod.POST)
	public String cadastrar(@ModelAttribute("time") @Valid Time time, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("usuario", time.getIntegrantes().iterator().next());
			return "time/novo"; 
		}
		timeDao.save(time);
		return "redirect:/index";
	}

	@RequestMapping("/mostra")
	public String mostra(int timeId, Model model, Principal principal) {
		model.addAttribute("usuario", usuarioDao.buscaPorNome(principal.getName()));
		model.addAttribute("time", timeDao.load(timeId));
		return "time/mostra";
	}

	@RequestMapping(value="/atualizar", method=RequestMethod.POST)
	public String atualizar(@ModelAttribute("time") @Valid Time time, BindingResult bindingResult, Model model, Principal principal) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("usuario", usuarioDao.buscaPorNome(principal.getName()));
			model.addAttribute("time", time);
			return "time/mostra"; 
		}
		timeDao.atualiza(time);
		return "redirect:/index";
	}
	
	@RequestMapping("/adicionaUsuario")
	public String adicionaIntegrante(@ModelAttribute("usuario") Usuario usuario) {
		Usuario usuarioLoaded = usuarioDao.buscaPorNome(usuario.getNome());
		Time timeLoaded = timeDao.load(usuario.getTimes().get(0).getId());
		timeDao.adicionaUsuarioNoTime(usuarioLoaded, timeLoaded);
		return "redirect:/time/mostra?id=" + timeLoaded.getId();
	}
	
	@RequestMapping(value="/removeUsuario", method=RequestMethod.GET)
	@ResponseBody
	public String removeIntegrante(@RequestParam Integer usuarioId, @RequestParam Integer timeId) {		
		timeDao.removeUsuarioDo(timeId, usuarioId);
		return "ok";
	}
}
