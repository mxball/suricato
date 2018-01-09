package com.suricatoagil.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Time {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="Nome do time não pode ser vazio")
	@Column(unique = true)
	private String nome;
	
	@ManyToMany
	@JoinTable(name="Time_Usuario", joinColumns=@JoinColumn(name="time_id"), inverseJoinColumns=@JoinColumn(name="usuario_id"))
	private List<Usuario> integrantes = new ArrayList<>();
	
	@OneToMany(mappedBy="time")
	private Set<Retrospectiva> retrospectivas = new HashSet<>();
	
	@NotBlank(message="Escolha a cor do seu time")
	private String cor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Usuario> getConjuntoIntegrantes() {
		return new HashSet<>(integrantes);
	}
	
	public List<Usuario> getIntegrantes() {
		return this.integrantes;
	}
	
	public void setIntegrantes(List<Usuario> integrantes) {
		this.integrantes = integrantes;
	}

	public Set<Retrospectiva> getRetrospectivas() {
		return retrospectivas;
	}

	public void setRetrospectivas(Set<Retrospectiva> restrospectivas) {
		this.retrospectivas = restrospectivas;
	}

	public Set<Retrospectiva> getRetrospectivasAbertas() {
		return this.retrospectivas.stream().filter(retro -> retro.isAberta()).collect(Collectors.toSet());
	}
	
	public Set<Retrospectiva> getRetrospectivasEncerradas() {
		return this.retrospectivas.stream().filter(retro -> !retro.isAberta()).collect(Collectors.toSet());
	}
	
	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
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
		Time other = (Time) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
