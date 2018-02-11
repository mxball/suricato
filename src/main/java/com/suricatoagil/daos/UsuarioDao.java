package com.suricatoagil.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.suricatoagil.models.Usuario;
import com.suricatoagil.viewmodels.AdicionaUsuarioTimeDTO;

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

	public List<AdicionaUsuarioTimeDTO> listaUsuariosComNomeParecidoCom(String nome) {
		return manager.createQuery("select new " + AdicionaUsuarioTimeDTO.class.getName() + "(u.nome, u.id) from Usuario u where nome like :nome", AdicionaUsuarioTimeDTO.class)
					.setParameter("nome", "%" + nome + "%")
					.getResultList();
	}

	public boolean jahExisteUsuarioChamado(String nome) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.nome = :nome", Usuario.class)
				.setParameter("nome", nome)
				.getResultList();
		return usuarios.size() > 0;
	}
	
	public List<Usuario> listaTodos() {
		return manager.createQuery("from Usuario u").getResultList();
	}
	
	public void atualiza(Usuario usuario) {
		manager.merge(usuario);
	}

}
