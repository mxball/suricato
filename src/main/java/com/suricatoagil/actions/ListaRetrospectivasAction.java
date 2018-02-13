package com.suricatoagil.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suricatoagil.daos.RetrospectivaDao;
import com.suricatoagil.daos.TimeDao;
import com.suricatoagil.models.Retrospectiva;
import com.suricatoagil.models.Time;
import com.suricatoagil.models.Usuario;
import com.suricatoagil.viewmodels.TimeRetrospectivasViewModel;

@Repository
public class ListaRetrospectivasAction {

	@Autowired
	private RetrospectivaDao retrospectivaDao;
	
	@Autowired
	private TimeDao timeDao;	
	
	public TimeRetrospectivasViewModel geraListaRetrospectivasAbertasDo(int timeId) {
		 Time time = timeDao.load(timeId);
		 List<Retrospectiva> retrospectivas = retrospectivaDao.listaRetrospectivasAbertasDoTime(timeId);
		 return new TimeRetrospectivasViewModel(time, retrospectivas);
	}
	
	public TimeRetrospectivasViewModel geraListaRetrospectivasFechadasDo(int timeId) {
		 Time time = timeDao.load(timeId);
		 List<Retrospectiva> retrospectivas = retrospectivaDao.listaRetrospectivasEncerradasDoTime(timeId);
		 return new TimeRetrospectivasViewModel(time, retrospectivas);
	}

	public Object geraListaRetrospectivasAbertasDo(Usuario usuario) {
		 List<Retrospectiva> retrospectivas = retrospectivaDao.listaRetrospectivasAbertasPessoaisDo(usuario);
		 return new TimeRetrospectivasViewModel(usuario, retrospectivas);
	}

	public Object geraListaRetrospectivasFechadasDo(Usuario usuario) {
		List<Retrospectiva> retrospectivas = retrospectivaDao.listaRetrospectivasFechadasPessoaisDo(usuario);
		 return new TimeRetrospectivasViewModel(usuario, retrospectivas);
	}
	
}
