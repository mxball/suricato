package br.usp.suricato.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
		if(comentario.isExcluir()){
			Comentario aExcluir = manager.getReference(Comentario.class, comentario.getId());
			manager.remove(aExcluir);
		}
	}

	public void saveAsync(Comentario comentario) {
		EntityManagerFactory managerFactory = manager.getEntityManagerFactory();
		EntityManager entityManager = managerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(comentario);	
		transaction.commit();		
	}

	public void removeAsync(Comentario comentario) {
		EntityManagerFactory managerFactory = manager.getEntityManagerFactory();
		EntityManager entityManager = managerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createQuery("delete from Comentario where id = :id").setParameter("id", comentario.getId()).executeUpdate();
		transaction.commit();
	}

	public Comentario load(int id) {
		return manager.find(Comentario.class, id);
	}

	public void updateAsync(Comentario comentario) {
		EntityManagerFactory managerFactory = manager.getEntityManagerFactory();
		EntityManager entityManager = managerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(comentario);
		transaction.commit();
	}
	
}
