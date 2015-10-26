package br.usp.suricato.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usp.suricato.models.Time;
import br.usp.suricato.models.Usuario;

@Repository
public class TimeDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Time time) {
		manager.persist(time);
	}
	
	
}
