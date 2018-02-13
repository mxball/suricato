package com.suricatoagil.daos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.suricatoagil.models.Retrospectiva;
import com.suricatoagil.models.Usuario;

@Repository
public class RetrospectivaDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void salva(Retrospectiva retrospectiva) {
		manager.persist(retrospectiva);
	}

	public List<Retrospectiva> listaRetrospectivasAbertasPessoaisDo(Usuario usuario) {
		return manager.createQuery("from Retrospectiva r where r.criador = :usuario and r.time is null and (r.dataFim >= :hoje or r.dataFim is null)", Retrospectiva.class)
					.setParameter("usuario", usuario)
					.setParameter("hoje", LocalDate.now())
					.getResultList();
	}
	

	public List<Retrospectiva>listaRetrospectivasFechadasPessoaisDo(Usuario usuario) {
		return manager.createQuery("from Retrospectiva r where r.criador = :usuario and r.time is null and r.dataFim < :hoje", Retrospectiva.class)
				.setParameter("usuario", usuario)
				.setParameter("hoje", LocalDate.now())
				.getResultList();
	}
	
	public List<Retrospectiva> listaRetrospectivasAbertasDoUsuario(Integer idUsuario) {
		return manager.createQuery("select r from Retrospectiva r where r.criador.id = :id and (r.dataFim >= :hoje or r.dataFim is null)", Retrospectiva.class)
					.setParameter("id", idUsuario)
					.setParameter("hoje", LocalDate.now())
					.getResultList();
	}
	
	public List<Retrospectiva> listaRetrospectivasEncerradasDoUsuario(Integer idUsuario) {
		return manager.createQuery("select r from Retrospectiva r where r.criador.id = :id and r.dataFim < :hoje", Retrospectiva.class)
				.setParameter("id", idUsuario)
				.setParameter("hoje", LocalDate.now())
				.getResultList();
	}

	public List<Retrospectiva> listaRetrospectivasAbertasDoTime(Integer idTime) {
		return manager.createQuery("select r from Retrospectiva r where r.time.id = :id and (r.dataFim >= :hoje or r.dataFim is null)", Retrospectiva.class)
				.setParameter("id", idTime)
				.setParameter("hoje", LocalDate.now())
				.getResultList();
	}

	public List<Retrospectiva> listaRetrospectivasEncerradasDoTime(Integer idTime) {
		return manager.createQuery("select r from Retrospectiva r where r.time.id = :id and r.dataFim < :hoje", Retrospectiva.class)
				.setParameter("id", idTime)
				.setParameter("hoje", LocalDate.now())
				.getResultList();
	}

	public Retrospectiva get(Integer idRetrospectiva) {
		return manager.find(Retrospectiva.class, idRetrospectiva);
	}

	public Retrospectiva loadWebSocket(Integer retrospectivaId) {
		return (Retrospectiva) manager.createQuery("select r from Retrospectiva r left join fetch r.time t left join fetch t.integrantes where r.id = :id")
					.setParameter("id", retrospectivaId)
					.getSingleResult();
	}

	public List<Retrospectiva> buscaRetrosPessoaisDo(Usuario usuario) {
		return manager.createQuery("from Retrospectiva r where r.criador = :usuario and r.time is null", Retrospectiva.class)
					.setParameter("usuario", usuario)
					.getResultList();
	}
	
}
