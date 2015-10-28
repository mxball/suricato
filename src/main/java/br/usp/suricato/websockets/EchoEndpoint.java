package br.usp.suricato.websockets;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

import br.usp.suricato.conf.ApplicationContextHolder;
import br.usp.suricato.daos.UsuarioDao;

@ServerEndpoint(value = "/echo", configurator = SpringConfigurator.class)
public class EchoEndpoint {

	@OnMessage
	public void handleMessage(Session session, String message) {
		UsuarioDao usuarioDao = ApplicationContextHolder.ctx.getBean(UsuarioDao.class);
	  	System.out.println(usuarioDao);
	  	System.out.println(usuarioDao.buscaPorNome(message));
	  	System.out.println(message);
	}

}