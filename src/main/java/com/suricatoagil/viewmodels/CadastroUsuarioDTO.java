package com.suricatoagil.viewmodels;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CadastroUsuarioDTO {

	@NotBlank
	@Size(min=5)
	private String nome;
	
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String senha;
	
	@NotBlank	
	private String confirmaSenha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
