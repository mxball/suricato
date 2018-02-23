package com.suricatoagil.viewmodels;

import org.hibernate.validator.constraints.NotEmpty;

public class TrocaSenhaDTO {

	@NotEmpty
	private String email;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
