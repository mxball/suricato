package com.suricatoagil.daos;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.suricatoagil.models.Usuario;

@Repository
public class UserDetailsServiceDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		Usuario usuario = (Usuario) em.createQuery("select u from Usuario u where u.nome = :nome")
									.setParameter("nome", username)
									.getSingleResult();
		List<SimpleGrantedAuthority> authorities = Arrays.asList( new SimpleGrantedAuthority( usuario.getPermissao().getNome() ) );
		return new User(usuario.getNome(), usuario.getSenha(), authorities);
	}

}