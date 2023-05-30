package com.Clinica.service;

import com.Clinica.Controller.DTO.PatientInputDTO;
import com.Clinica.Controller.DTO.PatientOutputDTO;
import com.Clinica.entity.Patient;

import java.util.List;

public interface PatientService {
    List<PatientOutputDTO> getAllPatients();

    PatientOutputDTO getPatientById(Long id);

    PatientOutputDTO addPatient(PatientInputDTO patientInputDto);

    PatientOutputDTO updatePatient(Long id, PatientInputDTO patientInputDto);

    void deletePatient(Long id);
}
