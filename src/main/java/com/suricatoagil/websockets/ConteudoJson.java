package com.suricatoagil.websockets;

import com.suricatoagil.models.Comentario;
import com.suricatoagil.models.PostIt;

public class ConteudoJson {

	private String tipoConteudo;
	
	private String operacao;
	
	private String texto;
	
	private Integer id;
	
	private Double posicaoHorizontal;
	
	private Double posicaoVertical;
	
	private Integer largura;
	
	private Integer altura;
	
	private String cor;

	private int numeroVotos;
	
	private int numeroDeslikes;

	public String getTipoConteudo() {
		return tipoConteudo;
	}

	public void setTipoConteudo(String tipoConteudo) {
		this.tipoConteudo = tipoConteudo;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPosicaoHorizontal() {
		return posicaoHorizontal;
	}

	public void setPosicaoHorizontal(Double posicaoHorizontal) {
		this.posicaoHorizontal = posicaoHorizontal;
	}

	public Double getPosicaoVertical() {
		return posicaoVertical;
	}

	public void setPosicaoVertical(Double posicaoVertical) {
		this.posicaoVertical = posicaoVertical;
	}

	public Integer getLargura() {
		return largura;
	}

	public void setLargura(Integer largura) {
		this.largura = largura;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public int getNumeroVotos() {
		return numeroVotos;
	}
	
	public void setNumeroVotos(int numeroVotos) {
		this.numeroVotos = numeroVotos;
	}
	
	public int getNumeroDeslikes() {
		return numeroDeslikes;
	}
	
	public void setNumeroDeslikes(int numeroDeslikes) {
		this.numeroDeslikes = numeroDeslikes;
	}

	@Override
	public String toString() {
		return "ConteudoJson [tipoConteudo=" + tipoConteudo + ", operacao="
				+ operacao + ", texto=" + texto + ", id=" + id
				+ ", posicaoHorizontal=" + posicaoHorizontal
				+ ", posicaoVertical=" + posicaoVertical + ", largura="
				+ largura + ", altura=" + altura + ", cor=" + cor
				+ ", numeroVotos=" + numeroVotos + ", numeroDeslikes=" + numeroDeslikes+ "]";
	}

	public PostIt getPostIt() {
		PostIt postIt = new PostIt(this.texto, this.posicaoHorizontal,
				this.posicaoVertical, this.cor, this.getNumeroVotos(), this.getNumeroDeslikes());
		if(this.id != 0) {
			postIt.setId(this.id);
		}
		return postIt;
	}

	public Comentario getComentario() {
		Comentario comentario = new Comentario(this.texto, this.posicaoHorizontal,
										this.posicaoVertical, this.largura,	this.altura);
		if (this.id != 0) {
			comentario.setId(this.id);
		}
		return comentario;
	}

	public boolean isValido() {
		return tipoConteudo != null && !tipoConteudo.isEmpty() && operacao != null && !operacao.isEmpty()
				&& texto != null && posicaoHorizontal != null && posicaoHorizontal >= 0 
				&& posicaoVertical != null && posicaoVertical >= 0 && largura != null && largura > 0 
				&& altura != null && altura > 0 && cor != null && numeroVotos >= 0 
				&& numeroDeslikes >= 0;
	}

}
