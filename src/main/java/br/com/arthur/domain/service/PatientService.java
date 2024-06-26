package br.com.arthur.domain.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.arthur.domain.entity.Patient;
import br.com.arthur.domain.repository.PatientRepository;
import br.com.arthur.exception.BusinessException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientService {

	private final PatientRepository repository;
	
	public PatientService(PatientRepository repository) {
		this.repository = repository;
	}
	
	public Patient save(Patient patient) {
		
		boolean cpfExists = false;
		
		Optional<Patient> optPatient = repository.findByCpf(patient.getCpf());
		
		if (optPatient.isPresent()) {
			if (!optPatient.get().getId().equals(patient.getId())) {
				cpfExists = true;
			}
		}
		
		if(cpfExists) {
			throw new BusinessException("CPF already registered");
		}
		
		return repository.save(patient);
	}
	
	public Page<Patient> listAllByPage(int pageNumber, int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return repository.findAll(page); 
	}
	
	public Patient update(Long id, Patient patient) {
		Optional<Patient> optPatient = this.listById(id);
		
		if(optPatient.isEmpty()) {
			throw new BusinessException("Patient not registered");
		}
		patient.setId(id);
		return save(patient);
	}
	
	public Optional<Patient> listById(Long id) {
		return repository.findById(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	
}
