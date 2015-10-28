package br.usp.suricato.websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.websocket.Session;

public class Canal {
	private List<Session> users = new ArrayList<>();

	public void send(String message) {
		try {
			for (Session user : users) {
				if (user.isOpen()) {
					user.getBasicRemote().sendText(message);
				}
			}
		} catch (IOException e) {
			System.err.println("Problema ao enviar a mensagem para o usuario. " + e);
		}
	}

	public void add(Session session) {
		users.add(session);
	}

	public void remove(Session session) {
		users.remove(session);
	}
	
	@PostConstruct
	public void ping() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				send("Ping");
			}
		}, 15000, 15000);
	}
}
