package br.usp.suricato.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Retrospectiva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	
	@ManyToOne
	@NotNull
	private Lousa lousa;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="retrospectiva", cascade = CascadeType.ALL)
	private Set<PostIt> postIts = new HashSet<>();

	@OneToMany(fetch=FetchType.EAGER, mappedBy="retrospectiva", cascade = CascadeType.ALL)
	private Set<Comentario> comentarios = new HashSet<>();
	
	@ManyToOne
	@NotNull
	private Usuario criador;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate dataFim;
	
	@ManyToOne
	private Time time;

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

	public Set<PostIt> getPostIts() {
		System.out.println(postIts.size());
		return postIts;
	}

	public void setPostIts(Set<PostIt> postIts) {
		this.postIts = postIts;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	public boolean isAberta() {
		if(this.dataFim == null) {
			return true;
		}
		return this.dataFim.isAfter(LocalDate.now()) || this.dataFim.isEqual(LocalDate.now());
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public boolean isUsuarioAutorizado(Usuario usuario) {
		if(this.criador.equals(usuario) || (this.time != null && this.time.getIntegrantes().contains(usuario))) {
			return true;
		}
		return false;
	}

	public String getNome() {
		if(nome == null || nome.isEmpty()) {
			StringBuilder builder = new StringBuilder();
			builder.append(this.lousa.getNome());
			if(this.dataFim != null) {
				builder.append(" - " + this.dataFim);
			}
			return builder.toString();
		}
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
