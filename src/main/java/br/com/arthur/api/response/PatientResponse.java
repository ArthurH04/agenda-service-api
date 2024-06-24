package br.com.arthur.api.response;

public class PatientResponse {

	private Long id;
	private String name;
	private String surname;
	private String cpf;
	private String email;
	
	public PatientResponse() {
	}

	public PatientResponse(Long id, String name, String surname, String cpf, String email) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.cpf = cpf;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
