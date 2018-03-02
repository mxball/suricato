package com.suricatoagil.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.suricatoagil.models.Time;
import com.suricatoagil.models.Usuario;

@Repository
public class TimeDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Time time) {
		manager.persist(time);
	}

	public Time load(Integer id) {
		return (Time) manager.createQuery("select t from Time t where t.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}

	public void atualiza(Time timeLoaded) {
		manager.merge(timeLoaded);
	}

	public void adicionaUsuarioNoTime(Usuario usuario, Time time) {
		manager.createNativeQuery("insert into time_usuario (time_id, usuario_id) values (:time, :usuario)")
			.setParameter("time", time.getId()).setParameter("usuario", usuario.getId()).executeUpdate();
	}

	public List<Time> buscaTimesDo(Usuario usuario) {
		return manager.createQuery("select distinct t from Time t join t.integrantes i where i.nome = :nome and i.id = :id ", Time.class)
				.setParameter("nome", usuario.getNome())
				.setParameter("id", usuario.getId())
				.getResultList();
	}

	public void removeUsuarioDo(int timeId, int usuarioId) {
		manager.createNativeQuery("delete from time_usuario where time_id = :timeId and usuario_id = :usuarioId")
			.setParameter("timeId", timeId)
			.setParameter("usuarioId", usuarioId)
			.executeUpdate();
		
	}

	public boolean jahExisteTimeChamado(String nome) {
		List<Time> times= manager.createQuery("select t from Time t where t.nome = :nome", Time.class)
				.setParameter("nome", nome)
				.getResultList();
		return times.size() > 0;
	}

	public boolean jahExisteOutroTimeChamado(String nome, Integer id) {
		List<Time> times= manager.createQuery("select t from Time t where t.nome = :nome and t.id != :id", Time.class)
				.setParameter("nome", nome)
				.setParameter("id", id)
				.getResultList();
		return times.size() > 0;
	}
	
	
}
