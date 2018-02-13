package com.suricatoagil.viewmodels;

public class TimeRetrosDTO {

	private int quantidadeDeRetros;
	
	private int quantidadeIntegrantes;
	
	private String nomeTime;
	
	private String cor;
	
	private Integer timeId;
	
	public TimeRetrosDTO(int quantidadeDeRetros, int quantidadeDeIntegrantes, String nomeTime, String cor) {
		this.quantidadeDeRetros = quantidadeDeRetros;
		quantidadeIntegrantes = quantidadeDeIntegrantes;
		this.nomeTime = nomeTime;
		this.cor = cor;
	}

	public TimeRetrosDTO(int quantidadeDeRetros, int quantidadeDeIntegrantes, Integer timeId, String nome, String cor) {
		this(quantidadeDeRetros, quantidadeDeIntegrantes, nome, cor);
		this.timeId = timeId;
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
	
	public Integer getTimeId() {
		return timeId;
	}
	
	public boolean isPessoal() {
		return timeId == null;
	}
	
}
