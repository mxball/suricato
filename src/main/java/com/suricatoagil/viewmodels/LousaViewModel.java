package com.suricatoagil.viewmodels;

import com.suricatoagil.models.Retrospectiva;

public class LousaViewModel {

	private int retroId;
	
	private String endereco;
	
	private String nome;
	
	public LousaViewModel(Retrospectiva retrospectiva) {
		this.retroId = retrospectiva.getId();
		this.endereco = retrospectiva.getLousa().getEndereco();
		this.nome = retrospectiva.getLousa().getNome();
	}

	public int getRetroId() {
		return retroId;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getNome() {
		return nome;
	}
	
}
