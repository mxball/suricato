package br.usp.suricato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class PostIt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String conteudo;

	@NotNull
	private Double posicaoHorizontal;

	@NotNull
	private Double posicaoVertical;
	
	private boolean excluir;

	@NotNull
	private String cor;
	
	@ManyToOne
	private Retrospectiva retrospectiva;

	/**
	 * @deprecated
	 */
	public PostIt(){}
	
	public PostIt(String conteudo, Double posicaoHorizontal, Double posicaoVertical, String cor, Retrospectiva retrospectiva) {
		this.conteudo = conteudo;
		this.posicaoHorizontal = posicaoHorizontal;
		this.posicaoVertical = posicaoVertical;
		this.cor = cor;
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

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Retrospectiva getRetrospectiva() {
		return retrospectiva;
	}

	public void setRetrospectiva(Retrospectiva retrospectiva) {
		this.retrospectiva = retrospectiva;
	}

	public boolean isExcluir() {
		return excluir;
	}

	public void setExcluir(boolean excluir) {
		this.excluir = excluir;
	}

}
