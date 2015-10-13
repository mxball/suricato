package br.usp.suricato.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usp.suricato.models.Comentario;
import br.usp.suricato.models.PostIt;
import br.usp.suricato.models.Retrospectiva;

@Repository
public class RetrospectivaDao {

	@PersistenceContext
	private EntityManager manager;

	public void salva(Retrospectiva retrospectiva) {
		manager.persist(retrospectiva);
	}

	public void atualiza(Retrospectiva retrospectiva) {
		for (PostIt postIt  : retrospectiva.getPostIts()) {
			if(postIt.getId() == null) {
				manager.persist(postIt);
			} else {
				manager.merge(postIt);
			}
		}
		for (Comentario comentario  : retrospectiva.getComentarios()) {
			System.out.println();
			if(comentario.getId() == null) {
				manager.persist(comentario);
			} else {
				manager.merge(comentario);
			}
		}
		manager.merge(retrospectiva);		
	}
	
}
