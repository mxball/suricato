package com.suricatoagil.viewmodels;

public class TimeRetrosDTO {

	private int quantidadeDeRetros;
	
	private int quantidadeIntegrantes;
	
	private String nomeTime;
	
	private String cor;
	
	public TimeRetrosDTO(int quantidadeDeRetros, int quantidadeDeIntegrantes, String nomeTime, String cor) {
		this.quantidadeDeRetros = quantidadeDeRetros;
		quantidadeIntegrantes = quantidadeDeIntegrantes;
		this.nomeTime = nomeTime;
		this.cor = cor;
	}

	public int getQuantidadeDeRetros() {
		return quantidadeDeRetros;
	}

	public int getQuantidadeIntegrantes() {
		return quantidadeIntegrantes;
	}

	public String getNomeTime() {
		return nomeTime;
	}

	public String getCor() {
		return cor;
	}
	
}
