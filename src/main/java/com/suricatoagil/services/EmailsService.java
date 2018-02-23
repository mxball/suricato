package com.suricatoagil.services;

import java.io.UnsupportedEncodingException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import com.suricatoagil.models.Usuario;

@Repository
public class EmailsService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendRecoverEmail(Usuario usuario) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		Address origem = new InternetAddress("suricatoagil@gmail.com", "Suricato");
	    Address destino = new InternetAddress(usuario.getEmail(), usuario.getNome());
		
		message.setFrom(origem);
		message.setRecipient(Message.RecipientType.TO, destino);
		message.setSubject("Pedido de troca de senha");
		message.setContent("Funfou", "text/plain");
		javaMailSender.send(message);
	}

}
