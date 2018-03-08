package com.suricatoagil.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suricatoagil.daos.TokenSenhaDao;
import com.suricatoagil.daos.UsuarioDao;
import com.suricatoagil.models.TokenSenha;
import com.suricatoagil.models.Usuario;
import com.suricatoagil.services.EmailsService;
import com.suricatoagil.viewmodels.NovaSenhaDTO;
import com.suricatoagil.viewmodels.TrocaSenhaDTO;

@Controller
@Transactional
public class SenhaController {

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private TokenSenhaDao tokenSenhaDao;
	
	@Autowired
	private EmailsService emailsService;

	@RequestMapping(value="/senha/nova", method=RequestMethod.GET)
	public String formularioNovaSenha() {
		return "senha/nova";
	}
	
	@RequestMapping(value="/senha/email", method=RequestMethod.POST)
	public String formularioNovaSenha(@Valid TrocaSenhaDTO trocaSenhaDTO, BindingResult bindingResult) throws UnsupportedEncodingException, MessagingException {
		Usuario usuario = usuarioDao.buscaPorEmail(trocaSenhaDTO.getEmail());
		if(usuario == null) {
			bindingResult.rejectValue("email", "senha.usuario.desconhecido");
		}
		if(bindingResult.hasErrors()) {
			return "senha/nova";
		}
		TokenSenha tokenSenha = usuario.getTokenSenha();
		tokenSenhaDao.saveOrUpdate(tokenSenha);
		emailsService.sendRecoverEmail(tokenSenha);
		return "redirect:/login";
	}
	
	@RequestMapping(value="/senha/email/{token}", method=RequestMethod.GET)
	public String formularioNovaSenha(@PathVariable String token, Model model) {
		TokenSenha tokenSenha = tokenSenhaDao.carregaPorToken(token);
		if(tokenSenha == null) {
			return "redirect:/login";
		}
		model.addAttribute("tokenSenha", tokenSenha);
		return "senha/troca";
	}
	
	@RequestMapping(value="/senha/troca", method=RequestMethod.POST)
	public String formularioNovaSenha(@Valid NovaSenhaDTO novaSenhaDTO, BindingResult bindingResult, Model model) {
		TokenSenha tokenSenha = tokenSenhaDao.carregaPorToken(novaSenhaDTO.getToken());
		if(tokenSenha == null) {
			bindingResult.rejectValue("senha", "senha.usuario.desconhecido");
		}
		if(novaSenhaDTO.isSenhasDiferentes()) {
			bindingResult.rejectValue("senha", "senha.diferentes");
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("tokenSenha", tokenSenha);
			return "senha/troca";
		}
		Usuario usuario = tokenSenha.getUsuario();
		usuario.setSenha(novaSenhaDTO.getSenhaCriptada());
		tokenSenhaDao.remove(tokenSenha);
		return "redirect:/login";
		
	}
	
}
