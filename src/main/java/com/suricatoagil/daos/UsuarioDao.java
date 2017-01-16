package com.suricatoagil.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.suricatoagil.models.Usuario;

@Repository
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Usuario usuario) {
		manager.persist(usuario);
	}

	public Usuario buscaPorNome(String nome) {
		Usuario usuario = (Usuario) manager.createQuery("select u from Usuario u left join fetch u.times where u.nome = :nome")
												.setParameter("nome", nome)
												.getSingleResult();
		return usuario;
	}

	public List<Usuario> listaUsuariosComNomeParecidoCom(String nome) {
		return manager.createQuery("select distinct u from Usuario u where nome like :nome", Usuario.class)
					.setParameter("nome", "%" + nome + "%")
					.getResultList();
	}

	public boolean jahExisteUsuario(Usuario usuario) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.nome = :nome", Usuario.class)
										.setParameter("nome", usuario.getNome())
										.getResultList();
		return usuarios.size() > 0;
	}

}
