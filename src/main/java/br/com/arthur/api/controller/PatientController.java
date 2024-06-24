package br.com.arthur.api.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthur.api.mapper.PatientMapper;
import br.com.arthur.api.request.PatientRequest;
import br.com.arthur.api.response.PatientResponse;
import br.com.arthur.domain.entity.Patient;
import br.com.arthur.domain.service.PatientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/patient")
public class PatientController {

	private PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@PostMapping
	public ResponseEntity<PatientResponse> save(@Valid @RequestBody PatientRequest patientReq) {
		
		// Getting the request from the body and converting into a Patient
		Patient patient = PatientMapper.toPatient(patientReq);
		
		// Saving the patient
		Patient savePatient = patientService.save(patient);
		
		// As the save method returns a PatientResponse, we need to convert the Patient into a PatientResponse
		PatientResponse patientResponse = PatientMapper.toPatientResponse(savePatient);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
	}
	
	@GetMapping(path = "/{pageNum}/{pageSize}")
	public ResponseEntity<Page<PatientResponse>> listAllByPage(@PathVariable(name = "pageNum") int pageNum,
			@PathVariable(name = "pageSize") int pageSize) {
		Page<Patient> patients = patientService.listAllByPage(pageNum, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(PatientMapper.toPatientResponseList(patients));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<PatientResponse> listById(@PathVariable(name = "id") Long id) {
		Optional<Patient> optPatient = patientService.listById(id);
		
		if (optPatient.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}		
		
		Patient patient = optPatient.get();
		
		return ResponseEntity.status(HttpStatus.OK).body(PatientMapper.toPatientResponse(patient));
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<PatientResponse> update(@PathVariable(name = "id") Long id, @RequestBody PatientRequest patientRequest){
		Patient patient = PatientMapper.toPatient(patientRequest);
		Patient savePatient = patientService.update(id, patient);
		PatientResponse patientResponse = PatientMapper.toPatientResponse(savePatient);
		return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable(name = "id") Long id){
		patientService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
