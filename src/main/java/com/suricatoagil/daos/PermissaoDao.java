package com.suricatoagil.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.suricatoagil.models.Permissao;

@Repository
public class PermissaoDao {

	@PersistenceContext
	private EntityManager manager;

	public Permissao load(Permissao permissao) {
		return manager.find(Permissao.class, permissao.getId());
	}

	public Permissao getPermissaoUsuario() {
		return manager.createQuery("from Permissao p where p.nome = :usuario", Permissao.class)
					.setParameter("usuario", "Usuario").getSingleResult();
	}
	
}
