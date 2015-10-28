package br.usp.suricato.websockets;

import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import br.usp.suricato.conf.ApplicationContextHolder;
import br.usp.suricato.daos.ComentarioDao;
import br.usp.suricato.daos.PostItDao;
import br.usp.suricato.daos.RetrospectivaDao;
import br.usp.suricato.daos.UsuarioDao;
import br.usp.suricato.models.Comentario;
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
			if(conteudos[0].equals("postIt")) {
				PostItDao postItDao = ApplicationContextHolder.ctx.getBean(PostItDao.class);
				if(conteudos[1].equals("adiciona")) {
					PostIt postIt = new PostIt(conteudos[2], Double.parseDouble(conteudos[3]), Double.parseDouble(conteudos[4]), conteudos[5], retrospectiva);
					System.out.println(postIt);
					postItDao.saveAsync(postIt);
					channel.send(mensagem + "|" + postIt.getId());
				} else if(conteudos[1].equals("remove")) {
					PostIt postIt = postItDao.load(Integer.parseInt(conteudos[2]));
					postItDao.removeAsync(postIt);
					channel.send(mensagem);
				}
			} else if(conteudos[0].equals("comentario")) {
				ComentarioDao comentarioDao = ApplicationContextHolder.ctx.getBean(ComentarioDao.class);
				if(conteudos[1].equals("adiciona")) {
					Comentario comentario = new Comentario(conteudos[2], Double.parseDouble(conteudos[3]), Double.parseDouble(conteudos[4]), Integer.parseInt(conteudos[5]), Integer.parseInt(conteudos[6]), retrospectiva);
					comentarioDao.saveAsync(comentario);
					channel.send(mensagem + "|" + comentario.getId());
				}else if(conteudos[1].equals("remove")) {
					Comentario comentario = comentarioDao.load(Integer.parseInt(conteudos[2]));
					comentarioDao.removeAsync(comentario);
					channel.send(mensagem);
				}
			}
		}
	}

}