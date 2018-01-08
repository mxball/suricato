package com.suricatoagil.controllers;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suricatoagil.actions.CadastroUsuarioAction;
import com.suricatoagil.daos.UsuarioDao;
import com.suricatoagil.models.Usuario;
import com.suricatoagil.viewmodels.AdicionaUsuarioTimeDTO;
import com.suricatoagil.viewmodels.CadastroUsuarioDTO;

@Controller
@Transactional
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private CadastroUsuarioAction cadastroUsuarioAction;

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    return "redirect:index";
		}
		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}
		return "usuario/login";
	}
	
	@RequestMapping(value="/usuario/cadastro", method = RequestMethod.GET)
	public String cadastro() {
		return "usuario/cadastro";
	}
	
	@RequestMapping(value="/usuario/cadastro", method = RequestMethod.POST)
	public String novoUsuario(@ModelAttribute("usuario") @Valid CadastroUsuarioDTO cadastroUsuarioDTO, BindingResult bindingResult, Model model) {
		if(cadastroUsuarioDTO.isSenhasDiferentes()) {
			bindingResult.rejectValue("senha", "senha.diferentes", "As senhas estão diferentes!");
		}
		if(usuarioDao.jahExisteUsuarioChamado(cadastroUsuarioDTO.getNome())) {
			bindingResult.rejectValue("nome", "nome.existe", "Usuario " + cadastroUsuarioDTO.getNome() + " já existe");
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("usuarioDTO", cadastroUsuarioDTO);
			return "usuario/cadastro";
		}
		Usuario usuario = cadastroUsuarioAction.criaUsuario(cadastroUsuarioDTO);
		List<SimpleGrantedAuthority> authorities = Arrays.asList( new SimpleGrantedAuthority( usuario.getPermissao().getNome() ) );
		Authentication authentication = new UsernamePasswordAuthenticationToken(usuario.getNome(), usuario.getSenha(), authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		usuarioDao.save(usuario);
		return "redirect:/index";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied() {
	  return "erro/403";
	}
	
	@RequestMapping(value = "/usuario/busca", method = RequestMethod.GET)
	public @ResponseBody List<AdicionaUsuarioTimeDTO> busca(@RequestParam("term") String nome) {
		return usuarioDao.listaUsuariosComNomeParecidoCom(nome);
	}
	
}
