package com.suricatoagil.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.suricatoagil.models.TokenSenha;

@Repository
public class TokenSenhaDao {

	@PersistenceContext
	private EntityManager manager;

	public void saveOrUpdate(TokenSenha tokenSenha) {
		try {			
			TokenSenha tokenSenhaLoaded = (TokenSenha) manager.createQuery("select t from TokenSenha t where t.usuario = :usuario")
															.setParameter("usuario", tokenSenha.getUsuario())
															.getSingleResult();
			tokenSenhaLoaded.setToken(tokenSenha.getToken());
			manager.merge(tokenSenhaLoaded);
		} catch (NoResultException e) {
			manager.persist(tokenSenha);
		}
	}

	@SuppressWarnings("unchecked")
	public TokenSenha carregaPorToken(String token) {
		List<TokenSenha> lista = manager.createQuery("select t from TokenSenha t where t.token = :token")
									.setParameter("token", token)
									.getResultList();
		if(lista.size() == 1) {
			return lista.get(0);
		}
		return null;
	}

	public void remove(TokenSenha tokenSenha) {
		manager.remove(tokenSenha);
	}
	
}
