package com.suricatoagil.controllers;

import java.security.Principal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suricatoagil.daos.RetrospectivaDao;
import com.suricatoagil.daos.UsuarioDao;
import com.suricatoagil.models.Usuario;

@Controller
@Transactional
public class HomeController
{
   
	@Autowired
	private UsuarioDao usuarioDao;
	
	@RequestMapping(value={"/", "/index"})
	public String index(Model model, Principal principal) {
		String nomeDoUsuario = principal.getName();
		Usuario usuario = usuarioDao.buscaPorNome(nomeDoUsuario);
		model.addAttribute("usuario", usuario);
		return "index";
	}
}
