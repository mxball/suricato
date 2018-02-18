package com.suricatoagil.controllers;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import com.suricatoagil.actions.CadastroUsuarioAction;
import com.suricatoagil.daos.UsuarioDao;
import com.suricatoagil.models.Usuario;
import com.suricatoagil.storage.StorageFileNotFoundException;
import com.suricatoagil.storage.StorageService;
import com.suricatoagil.viewmodels.AdicionaUsuarioTimeDTO;
import com.suricatoagil.viewmodels.CadastroUsuarioDTO;
import com.suricatoagil.viewmodels.NovoUsuarioDTO;

@Controller
@Transactional
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private CadastroUsuarioAction cadastroUsuarioAction;	
	@Autowired
	private StorageService storageService;
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value={"/login", "/usuario/login"}, method = RequestMethod.GET)
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
	public String novoUsuario(@ModelAttribute("usuario") @Valid CadastroUsuarioDTO cadastroUsuarioDTO, BindingResult bindingResult, @RequestParam("file") MultipartFile file, Model model) {
		if(cadastroUsuarioDTO.isSenhasDiferentes()) {
			bindingResult.rejectValue("senha", "senha.diferentes", "As senhas estão diferentes!");
		}
		if(usuarioDao.jahExisteUsuarioComEmail(cadastroUsuarioDTO.getEmail())) {
			bindingResult.rejectValue("email", "email.existe", "E-mail " + cadastroUsuarioDTO.getEmail() + " já existe");
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
		if(!file.isEmpty()) {			
			String caminhoFoto = storageService.store(file, usuario.getNome());
			usuario.setFoto(caminhoFoto);
		}
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
	
	@RequestMapping(value="/usuario/edita", method=RequestMethod.GET)
	public String editaUsuario(Principal principal, Model model) {
		Usuario usuarioLogado = usuarioDao.buscaPorNome(principal.getName());
		model.addAttribute("usuario", new NovoUsuarioDTO(usuarioLogado));
		return "usuario/edita";
	}
	
	@RequestMapping(value="/usuario/edita", method=RequestMethod.POST)
	public String editaUsuario(@ModelAttribute("usuario") @Valid NovoUsuarioDTO novoUsuario, BindingResult bindingResult, @RequestParam("file") MultipartFile file, Principal principal, Model model) {
		Usuario usuarioLogado = usuarioDao.buscaPorNome(principal.getName());
		if(novoUsuario.isSenhasDiferentes()) {
			bindingResult.rejectValue("senha", "senha.diferentes", "As senhas estão diferentes!");
		}
		if(!usuarioLogado.getNome().equals(novoUsuario.getNome()) && usuarioDao.jahExisteUsuarioChamado(novoUsuario.getNome())) {
			bindingResult.rejectValue("nome", "nome.existe", "Usuario " + novoUsuario.getNome() + " já existe");
		}
		if(!novoUsuario.getEmail().equals(usuarioLogado.getEmail()) && usuarioDao.jahExisteUsuarioComEmail(novoUsuario.getEmail())) {
			bindingResult.rejectValue("email", "email.existe", "E-mail " + novoUsuario.getEmail() + " já existe");
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("usuario", novoUsuario);
			return "usuario/edita";
		}
		if(novoUsuario.hasSenha()) {
			usuarioLogado.setSenha(novoUsuario.getSenhaCriptada());
		}
		if(!usuarioLogado.getNome().equals(novoUsuario.getNome())) {
			usuarioLogado.setNome(novoUsuario.getNome());
		}
		if(!novoUsuario.getEmail().equals(usuarioLogado.getEmail())) {
			usuarioLogado.setEmail(novoUsuario.getEmail());
		}
		if(!file.isEmpty()) {			
			String caminhoFoto = storageService.store(file, usuarioLogado.getNome());
			usuarioLogado.setFoto(caminhoFoto);
		}
		usuarioDao.atualiza(usuarioLogado);
		model.addAttribute("usuario", new NovoUsuarioDTO(usuarioLogado));
		model.addAttribute("atualizado", "Usuário atualizado com sucesso");
		return "usuario/edita";
	}
	
	@ResponseBody
	@RequestMapping(value = "/usuario/perfil", method = RequestMethod.GET)
	public ResponseEntity<Resource> imagemDoUsuario(Principal principal) {
		Usuario usuarioLogado = usuarioDao.buscaPorNome(principal.getName());
		Resource resource;
		try {
			resource = storageService.loadAsResource(usuarioLogado.getNome());
		} catch(StorageFileNotFoundException ex) {
			resource = new ServletContextResource(servletContext, "/assets/images/defaultUser.png");			
		}
	    HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/usuario/perfil/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Resource> imagemDoUsuarioComNome(@PathVariable String nome) {
		Usuario usuario = usuarioDao.buscaPorNome(nome);
		Resource resource;
		try {
			resource = storageService.loadAsResource(usuario.getNome());
		} catch(StorageFileNotFoundException ex) {
			resource = new ServletContextResource(servletContext, "/assets/images/defaultUser.png");			
		}
	    HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
}
