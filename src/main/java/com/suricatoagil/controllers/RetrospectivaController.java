package com.suricatoagil.controllers;

import java.security.Principal;
import java.util.Calendar;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suricatoagil.actions.ListaRetrospectivasAction;
import com.suricatoagil.daos.ComentarioDao;
import com.suricatoagil.daos.LousaDao;
import com.suricatoagil.daos.PostItDao;
import com.suricatoagil.daos.RetrospectivaDao;
import com.suricatoagil.daos.TimeDao;
import com.suricatoagil.daos.UsuarioDao;
import com.suricatoagil.models.Retrospectiva;
import com.suricatoagil.models.Usuario;
import com.suricatoagil.viewmodels.AdicionaRetrospectivaDTO;
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
	private TimeDao timeDao;
	
	@Autowired
	private AtualizaRetrospectiva atualizaRetrospectiva;
	
	@Autowired
	private ListaRetrospectivasAction listaRetrospectivasAction;

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
	public String criaRestrospectiva(@Valid AdicionaRetrospectivaDTO adicionaRetrospectivaDTO, BindingResult result, Model model, Principal principal) {
		Usuario usuarioLogado = usuarioDao.buscaPorNome(principal.getName());
		if(result.hasErrors()) {
			model.addAttribute("lousas", lousaDao.lista());
			model.addAttribute("usuario", usuarioLogado);
			model.addAttribute("hoje", Calendar.getInstance());
			return "retrospectiva/nova";
		}
		Retrospectiva retrospectiva = new Retrospectiva();
		if(adicionaRetrospectivaDTO.getTimeId() != null) {
			retrospectiva.setTime(timeDao.load(adicionaRetrospectivaDTO.getTimeId()));
		}
		retrospectiva.setCriador(usuarioLogado);
		retrospectiva.setLousa(lousaDao.load(adicionaRetrospectivaDTO.getLousaId()));
		retrospectiva.setDataInicio(adicionaRetrospectivaDTO.getDataInicio());
		retrospectiva.setDataFim(adicionaRetrospectivaDTO.getDataFim());
		retrospectivaDao.salva(retrospectiva);
		return "redirect:mostra?id=" + retrospectiva.getId();
	}
	
	@RequestMapping("/nova")
	public String novaRetrospectiva(Integer timeId, Model model, Principal principal) {
		model.addAttribute("lousas", lousaDao.lista());
		model.addAttribute("usuario", usuarioDao.buscaPorNome(principal.getName()));
		model.addAttribute("hoje", Calendar.getInstance());
		model.addAttribute("timeId", timeId);
		return "retrospectiva/nova";
	}

	@RequestMapping("usuario/lista")
	public String listaRetrospectivasDoUsuario(Usuario usuario, Model model) {
		model.addAttribute("retrospectivasAbertas", retrospectivaDao.listaRetrospectivasAbertasDoUsuario(usuario.getId()));
		model.addAttribute("retrospectivasFechadas", retrospectivaDao.listaRetrospectivasEncerradasDoUsuario(usuario.getId()));
		return "retrospectiva/lista";
	}
	
	@RequestMapping("time/abertas")
	public String listaRetrospectivasAbertasDoTime(int timeId, Principal principal, Model model) {
		Usuario usuario = usuarioDao.buscaPorNome(principal.getName());
		model.addAttribute("usuario", usuario);
		model.addAttribute("timeRetrospectivas", listaRetrospectivasAction.geraListaRetrospectivasAbertasDo(timeId));
		return "retrospectiva/lista";
	}
	
	@RequestMapping("time/fechadas")
	public String listaRetrospectivasFechadasDoTime(int timeId, Principal principal,Model model) {
		Usuario usuario = usuarioDao.buscaPorNome(principal.getName());
		model.addAttribute("usuario", usuario);
		model.addAttribute("timeRetrospectivas", listaRetrospectivasAction.geraListaRetrospectivasFechadasDo(timeId));
		return "retrospectiva/lista";
	}
	
	@RequestMapping("pessoal/abertas")
	public String listaRetrospectivasAbertasDoUsuario(Principal principal, Model model) {
		Usuario usuario = usuarioDao.buscaPorNome(principal.getName());
		model.addAttribute("usuario", usuario);
		model.addAttribute("timeRetrospectivas", listaRetrospectivasAction.geraListaRetrospectivasAbertasDo(usuario));
		return "retrospectiva/lista";
	}
	
	@RequestMapping("pessoal/fechadas")
	public String listaRetrospectivasFechadasDoUsuario(Principal principal, Model model) {
		Usuario usuario = usuarioDao.buscaPorNome(principal.getName());
		model.addAttribute("usuario", usuario);
		model.addAttribute("timeRetrospectivas", listaRetrospectivasAction.geraListaRetrospectivasFechadasDo(usuario));
		return "retrospectiva/lista";
	}

	@MessageMapping("/retrospectiva/asndjkahsdjhds/{retrospectivaId}/{nomeUsuario}")
	@SendTo("/message/retrospectiva/asndjkahsdjhds/{retrospectivaId}")
	public ConteudoJson atualizaRetrospectiva(ConteudoJson conteudo, @DestinationVariable("retrospectivaId") Integer retrospectivaId, @DestinationVariable("nomeUsuario") String nomeUsuario) {
		Retrospectiva retrospectiva = retrospectivaDao.loadWebSocket(retrospectivaId);
		if(conteudo.isValido() && (retrospectiva.isPublica() || (!nomeUsuario.isEmpty() && retrospectiva.isUsuarioAutorizado(usuarioDao.buscaPorNome(nomeUsuario))))) {
			atualizaRetrospectiva.atualiza(retrospectiva, conteudo);
			return conteudo;
		} else {
			return null;
		}
	}

}
