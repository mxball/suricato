package com.suricatoagil.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suricatoagil.daos.UsuarioDao;
import com.suricatoagil.models.Usuario;
import com.suricatoagil.services.EmailsService;
import com.suricatoagil.viewmodels.TrocaSenhaDTO;

@Controller
@Transactional
public class SenhaController {

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private EmailsService emailsService;

	@RequestMapping(value="/senha/nova", method=RequestMethod.GET)
	public String formularioNovaSenha() {
		return "senha/nova";
	}
	
	@RequestMapping(value="/senha/email", method=RequestMethod.POST)
	public String formularioNovaSenha(@Valid TrocaSenhaDTO trocaSenhaDTO, BindingResult bindingResult) throws UnsupportedEncodingException, MessagingException {
		if(bindingResult.hasErrors()) {
			return "senha/nova";
		}
		Usuario usuario = usuarioDao.buscaPorEmail(trocaSenhaDTO.getEmail());
		emailsService.sendRecoverEmail(usuario);
		return "redirect:/login";
	}
}
