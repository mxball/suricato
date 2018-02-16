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
	
	public String getCorGradiente() {
		if(cor.equals("9f53ed")) {
			return "6c18c3";
		} else if(cor.equals("68e1ff")) {
			return "24b0d3";
		} else if(cor.equals("ffb647")) {
			return "e59316";
		} else if(cor.equals("ff6885")) {
			return "d73d5b";
		} else if(cor.equals("6de069")) {
			return "4dcd49";
		} else if(cor.equals("699ce0")) {
			return "3571c1";
		} else if(cor.equals("eb5fea")) {
			return "c231c1";
		}
		return "000";
	}
	
	public Integer getTimeId() {
		return timeId;
	}
	
	public boolean isPessoal() {
		return timeId == null;
	}
	
}
