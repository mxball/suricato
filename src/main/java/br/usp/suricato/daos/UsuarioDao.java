package br.usp.suricato.daos;

import java.util.List;

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

	public List<Usuario> listaUsuariosComNomeParecidoCom(String nome) {
		return manager.createQuery("select distinct u from Usuario u where nome like :nome", Usuario.class)
					.setParameter("nome", "%" + nome + "%")
					.getResultList();
	}

}
