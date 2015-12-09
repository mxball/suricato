package br.usp.suricato.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usp.suricato.models.Time;

@Repository
public class TimeDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Time time) {
		manager.persist(time);
	}

	public Time load(Integer id) {
		return (Time) manager.createQuery("select t from Time t left join fetch t.retrospectivas "
				+ "left join fetch t.integrantes  where t.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}

	public void atualiza(Time timeLoaded) {
		manager.merge(timeLoaded);
	}
	
	
}
