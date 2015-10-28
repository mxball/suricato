package br.usp.suricato.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
	
	public void saveAsync(PostIt postIt) {
		EntityManagerFactory managerFactory = manager.getEntityManagerFactory();
		EntityManager entityManager = managerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(postIt);	
		transaction.commit();
	}

	public void removeAsync(PostIt postIt) {
		EntityManagerFactory managerFactory = manager.getEntityManagerFactory();
		EntityManager entityManager = managerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createQuery("delete from PostIt where id = :id").setParameter("id", postIt.getId()).executeUpdate();
		transaction.commit();		
	}

	public PostIt load(int id) {
		return manager.find(PostIt.class, id);
	}

}
