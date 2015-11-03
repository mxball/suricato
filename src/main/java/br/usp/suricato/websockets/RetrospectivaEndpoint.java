package br.usp.suricato.websockets;

import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import br.usp.suricato.conf.ApplicationContextHolder;
import br.usp.suricato.daos.RetrospectivaDao;
import br.usp.suricato.daos.UsuarioDao;
import br.usp.suricato.models.Retrospectiva;

@ServerEndpoint(
		value="/retrospectiva/asndjkahsdjhds/{retrospectivaId}/{nomeUsuario}",
		encoders = {ConteudoEncoder.class},
		decoders = {ConteudoDencoder.class}
)
public class RetrospectivaEndpoint {

	@Inject
	private Canal channel;
	
	@OnOpen
	public void novoUsuario(Session session, @PathParam("retrospectivaId") Integer retrospectivaId,
							@PathParam("nomeUsuario") String nomeUsuario) {
		UsuarioDao usuarioDao = ApplicationContextHolder.ctx.getBean(UsuarioDao.class);
		RetrospectivaDao retrospectivaDao = ApplicationContextHolder.ctx.getBean(RetrospectivaDao.class);
		Retrospectiva retrospectiva = retrospectivaDao.load(retrospectivaId);
		if(retrospectiva.isPublica() || (!nomeUsuario.isEmpty() && retrospectiva.isUsuarioAutorizado(usuarioDao.buscaPorNome(nomeUsuario)))) {
			channel.adiciona(session, retrospectiva);
		}
	}
	
	@OnMessage
	public void handleMessage(Session session, @PathParam("retrospectivaId") Integer retrospectivaId,
							@PathParam("nomeUsuario") String nomeUsuario, ConteudoJson conteudo) {
		UsuarioDao usuarioDao = ApplicationContextHolder.ctx.getBean(UsuarioDao.class);
		RetrospectivaDao retrospectivaDao = ApplicationContextHolder.ctx.getBean(RetrospectivaDao.class);
		Retrospectiva retrospectiva = retrospectivaDao.load(retrospectivaId);
		if(retrospectiva.isPublica() || (!nomeUsuario.isEmpty() && retrospectiva.isUsuarioAutorizado(usuarioDao.buscaPorNome(nomeUsuario)))) {
			AtualizaRetrospectiva.paraContexto(ApplicationContextHolder.ctx).atualiza(retrospectiva, conteudo);
			channel.enviaMensagem(conteudo, retrospectiva);
		}
	}

}