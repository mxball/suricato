package br.usp.suricato.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Retrospectiva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@NotNull
	private Lousa lousa;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="retrospectiva")
	private List<PostIt> postIts = new ArrayList<>();

	@OneToMany(fetch=FetchType.EAGER, mappedBy="retrospectiva")
	private List<Comentario> comentarios = new ArrayList<>();
	
	@ManyToOne
	@NotNull
	private Usuario criador;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Lousa getLousa() {
		return lousa;
	}

	public void setLousa(Lousa lousa) {
		this.lousa = lousa;
	}

	public List<PostIt> getPostIts() {
		return postIts;
	}

	public void setPostIts(List<PostIt> postIts) {
		this.postIts = postIts;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

}
