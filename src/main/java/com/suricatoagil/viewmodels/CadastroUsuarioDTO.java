package com.suricatoagil.viewmodels;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class CadastroUsuarioDTO {

	@NotBlank(message="Usuário não pode ser vazio")
	@Size(min=5, message="Nome do usuário deve conter pelo menos 5 caracteres")
	private String nome;

	@NotBlank(message="Senha não pode ser vazia")
	private String senha;
	
	@NotBlank(message="Confirmação de senha não pode ser vazia")	
	private String confirmaSenha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	 	
}
