package com.suricatoagil.viewmodels;

import java.util.ArrayList;
import java.util.List;

import com.suricatoagil.models.Retrospectiva;
import com.suricatoagil.models.Time;
import com.suricatoagil.models.Usuario;

public class TimeRetrospectivasViewModel {

	private String titulo;
	private String timeId;
	private String cor;
	private List<String> integrantes = new ArrayList<>();
	private List<LousaViewModel> lousas = new ArrayList<>();

	private TimeRetrospectivasViewModel(List<Retrospectiva> retrospectivas) {		
		for (Retrospectiva retrospectiva : retrospectivas) {
			this.lousas.add(new LousaViewModel(retrospectiva));
		}
	}
	
	public TimeRetrospectivasViewModel(Time time, List<Retrospectiva> retrospectivas) {
		this(retrospectivas);
		this.titulo = time.getNome();
		this.timeId = time.getId().toString();
		this.cor = time.getCor();
		for (Usuario usuario : time.getIntegrantes()) {
			this.integrantes.add(usuario.getNome());
		}
	}

	public TimeRetrospectivasViewModel(Usuario usuario, List<Retrospectiva> retrospectivas) {
		this(retrospectivas);
		this.titulo = "Pessoal";
		this.timeId = "";
		this.cor = "eb5fea";
		this.integrantes.add(usuario.getNome());
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getTimeId() {
		return timeId;
	}
	
	public boolean isDeTime() {
		return !timeId.isEmpty();
	}
	
	public boolean isPessoal() {
		return timeId == null;
	}
	
	public String getCor() {
		return cor;
	}
	
	public String getCorGradiente() {
		if(cor.equals("9f53ed")) {
			return "6c18c3";
		} else if(cor.equals("68e1ff")) {
			return "24b0d3";
		} else if(cor.equals("ffb647")) {
			return "e59316";
		} else if(cor.equals("ff6885")) {
			return "d73d5b";
		} else if(cor.equals("6de069")) {
			return "4dcd49";
		} else if(cor.equals("699ce0")) {
			return "3571c1";
		} else if(cor.equals("eb5fea")) {
			return "c231c1";
		}
		return "000";
	}

	public List<String> getIntegrantes() {
		return integrantes;
	}
	
	public int getNumeroIntegrantes() {
		return integrantes.size();
	}

	public List<LousaViewModel> getLousas() {
		return lousas;
	}
	
	public int getNumeroRetrospectivas() {
		return lousas.size();
	}

	
}
