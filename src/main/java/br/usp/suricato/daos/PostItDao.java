package br.usp.suricato.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usp.suricato.models.PostIt;

@Repository
public class PostItDao {

	@PersistenceContext
	private EntityManager manager;

	public void saveOrUpdate(PostIt postIt) {
		if(postIt.getId() == null) {
			manager.persist(postIt);
		} else {
			manager.merge(postIt);
		}
		if(postIt.isExcluir()){
			PostIt aExcluir = manager.getReference(PostIt.class, postIt.getId());
			manager.remove(aExcluir);
		}
	}
	
}
