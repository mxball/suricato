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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message="Usuário não pode ser vazio")
	@Column(unique = true)
	@Size(min=5, message="Nome do usuário deve conter pelo menos 5 caracteres")
	private String nome;

	@NotBlank(message="Senha não pode ser vazia")
	private String senha;
	
	@ManyToOne
	@NotNull
	private Permissao permissao;
	
	private boolean ativo = true;
	
	@ManyToMany
	@JoinTable(name="Time_Usuario", joinColumns=@JoinColumn(name="usuario_id"), inverseJoinColumns=@JoinColumn(name="time_id"))
	private List<Time> times = new ArrayList<>();
	
	@OneToMany(mappedBy="criador")
	private Set<Retrospectiva> retrospectivas = new HashSet<>();

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public List<Time> getTimes() {
		return times;
	}

	public Set<Time> getConjuntoTimes() {
		return times.stream().collect(Collectors.toSet());
	}
	
	public void setTimes(List<Time> times) {
		this.times = times;
	}

	public Set<Retrospectiva> getRetrospectivas() {
		return retrospectivas;
	}

	public void setRestrospectivas(Set<Retrospectiva> restrospectivas) {
		this.retrospectivas = restrospectivas;
	}
	
	public Set<Retrospectiva> getRetrospectivasAbertas() {
		return this.retrospectivas.stream().filter(retro -> retro.isAberta()).collect(Collectors.toSet());
	}
	
	public Set<Retrospectiva> getRetrospectivasEncerradas() {
		return this.retrospectivas.stream().filter(retro -> !retro.isAberta()).collect(Collectors.toSet());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
