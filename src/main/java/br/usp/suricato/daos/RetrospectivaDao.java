package br.usp.suricato.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usp.suricato.models.Comentario;
import br.usp.suricato.models.PostIt;
import br.usp.suricato.models.Retrospectiva;

@Repository
public class RetrospectivaDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PostItDao postItDao;
	
	@Autowired
	private ComentarioDao comentarioDao;

	public void salva(Retrospectiva retrospectiva) {
		manager.persist(retrospectiva);
	}

	public void atualiza(Retrospectiva retrospectiva) {
		for (PostIt postIt : retrospectiva.getPostIts()) {
			postItDao.saveOrUpdate(postIt);
		}
		for (Comentario comentario : retrospectiva.getComentarios()) {
			comentarioDao.saveOrUpdate(comentario);
		}
		manager.merge(retrospectiva);		
	}
	
}
