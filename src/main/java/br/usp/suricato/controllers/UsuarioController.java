package br.usp.suricato.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.usp.suricato.daos.PermissaoDao;
import br.usp.suricato.daos.UsuarioDao;
import br.usp.suricato.models.Usuario;

@Controller
@Transactional
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private PermissaoDao permissaoDao;

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
	
	@RequestMapping("/usuario/cadastro")
	public String cadastro() {
		return "usuario/cadastro";
	}
	
	@RequestMapping(value="/usuario/novo")
	public String novoUsuario(@Valid Usuario usuario, BindingResult bindingResult, Model model) {
		usuario.setPermissao(permissaoDao.load(usuario.getPermissao()));
		if (bindingResult.hasErrors()) {
			model.addAttribute("usuario", usuario);
			return "usuario/cadastro";
		}
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
	
}