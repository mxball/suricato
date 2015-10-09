package br.usp.suricato.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usp.suricato.models.Lousa;

@Repository
public class LousaDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Lousa> lista() {
		return manager.createQuery("select l from Lousa l", Lousa.class).getResultList();
	}

	public Lousa load(Integer id) {
		return manager.find(Lousa.class, id);
	}

}
