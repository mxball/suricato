package com.suricatoagil.controllers;

import java.security.Principal;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suricatoagil.daos.ComentarioDao;
import com.suricatoagil.daos.LousaDao;
import com.suricatoagil.daos.PostItDao;
import com.suricatoagil.daos.RetrospectivaDao;
import com.suricatoagil.daos.UsuarioDao;
import com.suricatoagil.models.Retrospectiva;
import com.suricatoagil.models.Time;
import com.suricatoagil.models.Usuario;
import com.suricatoagil.websockets.AtualizaRetrospectiva;
import com.suricatoagil.websockets.ConteudoJson;

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
	
	@Autowired
	private PostItDao postItDao;
	
	@Autowired
	private ComentarioDao comentarioDao;
	
	@Autowired
	private AtualizaRetrospectiva atualizaRetrospectiva;

	@RequestMapping("/mostra")
	public String mostraRetrospectiva(Integer id, Model model, Principal principal) {
		Retrospectiva retrospectiva = retrospectivaDao.get(id);
		if(!retrospectiva.isPublica()) {
			Usuario usuario = (principal == null) ? new Usuario() : usuarioDao.buscaPorNome(principal.getName());
			if(!retrospectiva.isUsuarioAutorizado(usuario) && !retrospectiva.isPublica()) {
				return "redirect:/index";
			}
		}
		model.addAttribute("retrospectiva", retrospectiva);
		model.addAttribute("postIts", postItDao.buscaPostIsDaRetrospectiva(retrospectiva));
		model.addAttribute("comentarios", comentarioDao.buscaComentariosDaRetrospectiva(retrospectiva));
		model.addAttribute("lousa", retrospectiva.getLousa());
		return "retrospectiva/mostra";
	}
	
	@RequestMapping("/cria")
	public String criaRestrospectiva(Retrospectiva retrospectiva, Model model) {
		if(retrospectiva.getTime().getId() == null) {
			retrospectiva.setTime(null);
		}
		retrospectiva.setCriador(usuarioDao.buscaPorNome(retrospectiva.getCriador().getNome()));
		retrospectiva.setLousa(lousaDao.load(retrospectiva.getLousa().getId()));
		retrospectivaDao.salva(retrospectiva);
		return "redirect:mostra?id=" + retrospectiva.getId();
	}
	
	@RequestMapping("/nova")
	public String novaRetrospectiva(Model model, Principal principal) {
		model.addAttribute("lousas", lousaDao.lista());
		model.addAttribute("usuario", usuarioDao.buscaPorNome(principal.getName()));
		model.addAttribute("hoje", Calendar.getInstance());
		return "retrospectiva/nova";
	}

	@RequestMapping("usuario/lista")
	public String listaRetrospectivasDoUsuario(Usuario usuario, Model model) {
		model.addAttribute("retrospectivasAbertas", retrospectivaDao.listaRetrospectivasAbertasDoUsuario(usuario.getId()));
		model.addAttribute("retrospectivasFechadas", retrospectivaDao.listaRetrospectivasEncerradasDoUsuario(usuario.getId()));
		return "retrospectiva/lista";
	}
	
	@RequestMapping("time/lista")
	public String listaRetrospectivasDoTime(Time time, Model model) {
		model.addAttribute("retrospectivasAbertas", retrospectivaDao.listaRetrospectivasAbertasDoTime(time.getId()));
		model.addAttribute("retrospectivasFechadas", retrospectivaDao.listaRetrospectivasEncerradasDoTime(time.getId()));
		return "retrospectiva/lista";
	}

	@MessageMapping("/retrospectiva/asndjkahsdjhds/{retrospectivaId}/{nomeUsuario}")
	@SendTo("/message/retrospectiva/asndjkahsdjhds/{retrospectivaId}")
	public ConteudoJson atualizaRetrospectiva(ConteudoJson conteudo, @DestinationVariable("retrospectivaId") Integer retrospectivaId, @DestinationVariable("nomeUsuario") String nomeUsuario) {
		Retrospectiva retrospectiva = retrospectivaDao.loadWebSocket(retrospectivaId);
		if(retrospectiva.isPublica() || (!nomeUsuario.isEmpty() && retrospectiva.isUsuarioAutorizado(usuarioDao.buscaPorNome(nomeUsuario)))) {
			atualizaRetrospectiva.atualiza(retrospectiva, conteudo);
			return conteudo;
		} else {
			return null;
		}
	}

}
