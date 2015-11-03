package br.usp.suricato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name="retrospectiva_id")
	private Retrospectiva retrospectiva;

	public PostIt(){}
	
	public PostIt(String conteudo, Double posicaoHorizontal, Double posicaoVertical, String cor) {
		this.conteudo = conteudo;
		this.posicaoHorizontal = posicaoHorizontal;
		this.posicaoVertical = posicaoVertical;
		this.cor = cor;
	}
	
	public PostIt(String conteudo, Double posicaoHorizontal, Double posicaoVertical, String cor, Retrospectiva retrospectiva) {
		this(conteudo, posicaoHorizontal, posicaoVertical, cor);
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
		PostIt other = (PostIt) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PostIt [id=" + id + ", conteudo=" + conteudo
				+ ", posicaoHorizontal=" + posicaoHorizontal
				+ ", posicaoVertical=" + posicaoVertical + ", excluir="
				+ excluir + ", cor=" + cor + ", retrospectiva=" + retrospectiva
				+ "]";
	}

	
	
}
