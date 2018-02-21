package com.suricatoagil.viewmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.suricatoagil.models.Retrospectiva;
import com.suricatoagil.models.Time;
import com.suricatoagil.models.Usuario;

public class MapaDeTimesDTO {

	private List<TimeRetrosDTO> timesRetrosAbertas = new ArrayList<>();
	
	private List<TimeRetrosDTO> timesRetrosFechadas = new ArrayList<>();
	
	public MapaDeTimesDTO(Usuario usuario, List<Time> times, List<Retrospectiva> retrospectivasPessoais) {
		
		for (Time time : times) {
			int numeroIntegrantes = time.getIntegrantes().size();
			Set<Retrospectiva> retrospectivasAbertas = time.getRetrospectivasAbertas();
			
			int quantidadeAbertas = retrospectivasAbertas.size();
							
			timesRetrosAbertas.add(new TimeRetrosDTO(quantidadeAbertas, numeroIntegrantes, time.getId(), time.getNome(), time.getCor()));
			Set<Retrospectiva> retrospectivasEncerradas = time.getRetrospectivasEncerradas();
			int quantidadeFechadas = retrospectivasEncerradas.size();
			if(quantidadeFechadas > 0) {
				timesRetrosFechadas.add(new TimeRetrosDTO(quantidadeFechadas, numeroIntegrantes, time.getId(), time.getNome(), time.getCor()));
			}
		}
		int quantidadeAbertas = 0;
		int quantidadeFechadas = 0;
		for (Retrospectiva retrospectiva : retrospectivasPessoais) {
			if(retrospectiva.isAberta()) {
				quantidadeAbertas++;
			} else {
				quantidadeFechadas++;
			}
		}
		timesRetrosAbertas.add(new TimeRetrosDTO(quantidadeAbertas, 1, "Pessoal", "eb5fea"));
		if(quantidadeFechadas > 0) {
			timesRetrosAbertas.add(new TimeRetrosDTO(quantidadeFechadas, 1, "Pessoal", "eb5fea"));
		}
	}
	
	public List<TimeRetrosDTO> getTimesRetrosAbertas() {
		return timesRetrosAbertas;
	}

	public List<TimeRetrosDTO> getTimesRetrosFechadas() {
		return timesRetrosFechadas;
	}
	
	public boolean isComRetrosPassadas() {
		return !this.timesRetrosAbertas.isEmpty() || !this.timesRetrosFechadas.isEmpty();
	}
}
