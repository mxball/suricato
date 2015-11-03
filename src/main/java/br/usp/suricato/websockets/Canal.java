package br.usp.suricato.websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.EncodeException;
import javax.websocket.Session;

import br.usp.suricato.models.Retrospectiva;

@ApplicationScoped
public class Canal {
	private Map<Retrospectiva, List<Session>> mapaUsuarios = new HashMap<>();

	public void enviaMensagemParaTodos(String message) {
		try {
			for (List<Session> lista : mapaUsuarios.values()) {
				for (Session usuarios : lista) {
					if (usuarios.isOpen()) {
						usuarios.getBasicRemote().sendText(message);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Problema ao enviar a mensagem para o usuario. " + e);
		}
	}
	
	public void enviaMensagem(ConteudoJson conteudo, Retrospectiva retrospectiva) {
		try {
			for (Session usuarios : mapaUsuarios.get(retrospectiva)) {
				if (usuarios.isOpen()) {
					usuarios.getBasicRemote().sendObject(conteudo);
				}
			}
		} catch (IOException | EncodeException e) {
			System.err.println("Problema ao enviar a mensagem para o usuario. " + e);
		}
	}

	public void adiciona(Session session, Retrospectiva retrospectiva) {
		if(mapaUsuarios.containsKey(retrospectiva)) {
			mapaUsuarios.get(retrospectiva).add(session);
		} else {
			ArrayList<Session> lista = new ArrayList<>();
			lista.add(session);
			mapaUsuarios.put(retrospectiva, lista);
		}
	}

	@PostConstruct
	public void ping() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				enviaMensagemParaTodos("Ping");
			}
		}, 15000, 15000);
	}

}
