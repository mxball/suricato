package com.suricatoagil.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suricatoagil.daos.RetrospectivaDao;
import com.suricatoagil.daos.TimeDao;
import com.suricatoagil.models.Retrospectiva;
import com.suricatoagil.models.Time;
import com.suricatoagil.models.Usuario;
import com.suricatoagil.viewmodels.MapaDeTimesDTO;

@Repository
public class MapaDeTimesAction {

	@Autowired
	private TimeDao timeDao;
	
	@Autowired
	private RetrospectivaDao retrospectivaDao;

	public MapaDeTimesDTO geraMapaDo(Usuario usuario) {
		List<Time> times = timeDao.buscaTimesDo(usuario);
		List<Retrospectiva> retrospectivasPessoais = retrospectivaDao.buscaRetrosPessoaisDo(usuario);
		return new MapaDeTimesDTO(usuario, times, retrospectivasPessoais);
	}
	
}
