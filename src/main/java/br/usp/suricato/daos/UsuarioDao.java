package br.usp.suricato.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usp.suricato.models.Usuario;

@Repository
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Usuario usuario) {
		manager.persist(usuario);
	}

	public Usuario buscaPorNome(String nome) {
		return (Usuario) manager.createQuery("select u from Usuario u where u.nome = :nome").setParameter("nome", nome).getSingleResult();
	}

}
