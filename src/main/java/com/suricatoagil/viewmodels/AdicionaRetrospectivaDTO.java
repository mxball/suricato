package com.suricatoagil.viewmodels;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class AdicionaRetrospectivaDTO {

	private Integer timeId;
	
	@NotNull
	private Integer lousaId;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate dataInicio;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate dataFim;

	public Integer getTimeId() {
		return timeId;
	}

	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
	}

	public Integer getLousaId() {
		return lousaId;
	}

	public void setLousaId(Integer lousaId) {
		this.lousaId = lousaId;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

}
