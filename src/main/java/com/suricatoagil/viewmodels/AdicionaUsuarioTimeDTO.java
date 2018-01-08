package com.suricatoagil.viewmodels;

public class AdicionaUsuarioTimeDTO {

	private String nome;
	
	private Integer id;

	public AdicionaUsuarioTimeDTO(String nome, Integer id) {
		this.nome = nome;
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}
	
}
