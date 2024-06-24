package br.com.arthur.api.request;

import jakarta.validation.constraints.NotBlank;

public class PatientRequest {

	@NotBlank(message = "Patient's name is required")
	private String name;
	
	@NotBlank(message = "Patient's surname is required")
	private String surname;
	
	@NotBlank(message = "Patient's cpf is required")
	private String cpf;
	
	@NotBlank(message = "Patient's email is required")
	private String email;

	public PatientRequest() {

	}

	public PatientRequest(String name, String surname, String cpf, String email) {
		this.name = name;
		this.surname = surname;
		this.cpf = cpf;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
