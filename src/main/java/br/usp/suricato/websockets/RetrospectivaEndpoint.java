package br.usp.suricato.websockets;

import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import br.usp.suricato.conf.ApplicationContextHolder;
import br.usp.suricato.daos.PostItDao;
import br.usp.suricato.daos.RetrospectivaDao;
import br.usp.suricato.daos.UsuarioDao;
import br.usp.suricato.models.PostIt;
import br.usp.suricato.models.Retrospectiva;
import br.usp.suricato.models.Usuario;

@ServerEndpoint("/retroespectiva/asndjkahsdjhds/{retrospectivaId}/{nomeUsuario}")
public class RetrospectivaEndpoint {

	@Inject
	private Canal channel;
	
	@OnOpen
	public void novoUsuario(Session session, @PathParam("retrospectivaId") Integer retrospectivaId,
							@PathParam("nomeUsuario") String nomeUsuario) {
		UsuarioDao usuarioDao = ApplicationContextHolder.ctx.getBean(UsuarioDao.class);
		RetrospectivaDao retrospectivaDao = ApplicationContextHolder.ctx.getBean(RetrospectivaDao.class);
		Usuario usuario = usuarioDao.buscaPorNome(nomeUsuario);
		Retrospectiva retrospectiva = retrospectivaDao.load(retrospectivaId);
		if(retrospectiva.isUsuarioAutorizado(usuario)) {
			channel.add(session);
		}
	}
	
	@OnMessage
	public void handleMessage(Session session, @PathParam("retrospectivaId") Integer retrospectivaId,
							@PathParam("nomeUsuario") String nomeUsuario, String mensagem) {
		UsuarioDao usuarioDao = ApplicationContextHolder.ctx.getBean(UsuarioDao.class);
		RetrospectivaDao retrospectivaDao = ApplicationContextHolder.ctx.getBean(RetrospectivaDao.class);
		Usuario usuario = usuarioDao.buscaPorNome(nomeUsuario);
		Retrospectiva retrospectiva = retrospectivaDao.load(retrospectivaId);
		if(retrospectiva.isUsuarioAutorizado(usuario)) {
			System.out.println(mensagem);
			String[] conteudos = mensagem.split("\\|");
			for (String string : conteudos) {
				System.out.println(string);
			}
			PostItDao postItDao = ApplicationContextHolder.ctx.getBean(PostItDao.class);
			PostIt postIt = new PostIt(conteudos[1], Double.parseDouble(conteudos[2]), Double.parseDouble(conteudos[3]), conteudos[4], retrospectiva);
			postItDao.saveAsync(postIt);
			channel.send(mensagem + "|" + postIt.getId());
		}
	}

}