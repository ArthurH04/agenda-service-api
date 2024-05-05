package br.com.arthur.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthur.domain.entity.Patient;
import br.com.arthur.domain.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	private PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping(path = "/{pageNum}/{pageSize}")
	public ResponseEntity<Page<Patient>> listAllByPage(@PathVariable(name = "pageNum") int pageNum,
			@PathVariable(name = "pageSize") int pageSize) {
		Page<Patient> patients = patientService.listAllByPage(pageNum, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(patients);
	}

	@PostMapping
	public ResponseEntity<Patient> save(@RequestBody Patient patient) {
		Patient savePatient = patientService.save(patient);
		return ResponseEntity.status(HttpStatus.CREATED).body(savePatient);
	}

}
