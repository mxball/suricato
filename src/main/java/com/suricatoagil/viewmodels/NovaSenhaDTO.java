package com.suricatoagil.viewmodels;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class NovaSenhaDTO {

	@NotEmpty
	private String token;

	@NotBlank
	private String senha;
	
	@NotBlank
	private String confirmaSenha;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public boolean isSenhasDiferentes() {
		return !this.senha.equals(this.confirmaSenha);
	}

	public String getSenhaCriptada() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(this.senha);
	}
	
	
}
