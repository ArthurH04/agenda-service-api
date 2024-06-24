package br.com.arthur.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.arthur.api.request.PatientRequest;
import br.com.arthur.api.response.PatientResponse;
import br.com.arthur.domain.entity.Patient;

public class PatientMapper {

	public static Patient toPatient(PatientRequest patientRequest) {

		Patient patient = new Patient();
		patient.setName(patientRequest.getName());
		patient.setSurname(patientRequest.getSurname());
		patient.setCpf(patientRequest.getCpf());
		patient.setEmail(patientRequest.getEmail());
		return patient;
	}

	public static PatientResponse toPatientResponse(Patient patient) {

		PatientResponse patientResponse = new PatientResponse();
		patientResponse.setId(patient.getId());
		patientResponse.setName(patient.getName());
		patientResponse.setSurname(patient.getSurname());
		patientResponse.setCpf(patient.getCpf());
		patientResponse.setEmail(patient.getEmail());
		return patientResponse;
	}

	public static Page<PatientResponse> toPatientResponseList(Page<Patient> patients) {
		
		List<PatientResponse> responses = patients.stream().map(PatientMapper::toPatientResponse)
				.collect(Collectors.toList());
//		List<PatientResponse> responses = new ArrayList<>();
//		for(Patient patient : patients) {
//			PatientResponse patientResponse = toPatientResponse(patient);
//			responses.add(patientResponse);
//		}
		return new PageImpl<>(responses, patients.getPageable(), patients.getTotalElements());
	}

}
