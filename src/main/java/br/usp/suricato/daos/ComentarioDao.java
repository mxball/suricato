package br.usp.suricato.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usp.suricato.models.Comentario;

@Repository
public class ComentarioDao {

	@PersistenceContext
	private EntityManager manager;

	public void saveOrUpdate(Comentario comentario) {
		if(comentario.getId() == null) {
			manager.persist(comentario);
		} else {
			manager.merge(comentario);
		}
	}
	
}
