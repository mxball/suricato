package com.suricatoagil.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.suricatoagil.models.PostIt;
import com.suricatoagil.models.Retrospectiva;

@Repository
public class PostItDao {

	@PersistenceContext
	private EntityManager manager;

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

	public void updateAsync(PostIt postIt) {
		EntityManagerFactory managerFactory = manager.getEntityManagerFactory();
		EntityManager entityManager = managerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(postIt);
		transaction.commit();
	}

	public void atualizaNumeroVotos(PostIt postIt) {
		EntityManagerFactory managerFactory = manager.getEntityManagerFactory();
		EntityManager entityManager = managerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createQuery("update PostIt set numeroVotos = :numero where id = :id")
					.setParameter("numero", postIt.getNumeroVotos())
					.setParameter("id", postIt.getId())
					.executeUpdate();
		transaction.commit();
	}

	public List<PostIt> buscaPostIsDaRetrospectiva(Retrospectiva retrospectiva) {
		return manager.createQuery("from PostIt where retrospectiva = :retrospectiva", PostIt.class)
					.setParameter("retrospectiva", retrospectiva)
					.getResultList();
	}

}
