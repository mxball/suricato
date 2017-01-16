package com.suricatoagil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@NotNull
	private Integer largura;

	@NotNull
	private Integer altura;

	@ManyToOne
	@JoinColumn(name="retrospectiva_id")
	private Retrospectiva retrospectiva;

	/**
	 * @deprecated
	 */
	public Comentario() {}
	
	public Comentario(String conteudo, double posicaoHorizontal, double posicaoVertical,
			int largura, int altura) {
		this.conteudo = conteudo;
		this.posicaoHorizontal = posicaoHorizontal;
		this.posicaoVertical = posicaoVertical;
		this.largura = largura;
		this.altura = altura;
	}
	
	public Comentario(String conteudo, double posicaoHorizontal, double posicaoVertical,
			int largura, int altura, Retrospectiva retrospectiva) {
		this(conteudo, posicaoHorizontal, posicaoVertical, largura, altura);
		this.retrospectiva = retrospectiva;
	}

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

	public Retrospectiva getRetrospectiva() {
		return retrospectiva;
	}

	public void setRetrospectiva(Retrospectiva retrospectiva) {
		this.retrospectiva = retrospectiva;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
