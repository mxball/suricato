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

import com.suricatoagil.helper.RequestHelper;
import com.suricatoagil.models.TokenSenha;
import com.suricatoagil.models.Usuario;

@Repository
public class EmailsService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private RequestHelper requestHelper;

	public void sendRecoverEmail(TokenSenha tokenSenha) throws UnsupportedEncodingException, MessagingException {
		Usuario usuario = tokenSenha.getUsuario();
		MimeMessage message = javaMailSender.createMimeMessage();
		Address origem = new InternetAddress("suricatoagil@gmail.com", "Suricato");
	    Address destino = new InternetAddress(usuario.getEmail(), usuario.getNome());
		
		message.setFrom(origem);
		message.setRecipient(Message.RecipientType.TO, destino);
		message.setSubject("Change Password");
		message.setContent(requestHelper.getBaseURL() + "/senha/email/" + tokenSenha.getToken(), "text/plain");
		javaMailSender.send(message);
	}

}
