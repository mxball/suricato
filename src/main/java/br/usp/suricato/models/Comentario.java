package br.usp.suricato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String conteudo;

	@NotNull
	private Double posicaoHorizontal;

	@NotNull
	private Double posicaoVertical;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
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

}
