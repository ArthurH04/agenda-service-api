package br.com.arthur.domain.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.arthur.domain.entity.Patient;
import br.com.arthur.domain.repository.PatientRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientService {

	private final PatientRepository repository;
	
	public PatientService(PatientRepository repository) {
		this.repository = repository;
	}
	
	public Patient save(Patient patient) {
		
		// TODO validate if 'CPF' doesn't exists
		
		return repository.save(patient);
	}
	
	public Page<Patient> listAllByPage(int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return repository.findAll(page); 
	}
	
	public Optional<Patient> listById(Long id) {
		return repository.findById(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	
}
