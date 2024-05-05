package br.com.arthur.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arthur.domain.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	Optional<Patient> findByCpf(String cpf);
	
}
