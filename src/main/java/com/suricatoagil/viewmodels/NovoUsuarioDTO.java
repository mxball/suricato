package com.suricatoagil.viewmodels;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.suricatoagil.models.Usuario;

public class NovoUsuarioDTO {
	@NotBlank(message="Usuário não pode ser vazio")
	@Size(min=5, message="Nome do usuário deve conter pelo menos 5 caracteres")
	private String nome;
	
	@NotBlank(message="E-mail não pode ser vazio")
	@Email(message="E-mail inválido")
	private String email;

	private String senha;
	
	private String confirmaSenha;

	public NovoUsuarioDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}
	
	/**
	 * Só para o spring ficar feliz
	 */
	@Deprecated()
	public NovoUsuarioDTO() {
	}
	
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

	public boolean hasSenha() {
		return !this.senha.isEmpty();
	}
}
