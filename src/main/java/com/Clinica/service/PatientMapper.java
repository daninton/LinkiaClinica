package com.Clinica.service;

import com.Clinica.Controller.DTO.PatientInputDTO;
import com.Clinica.Controller.DTO.PatientOutputDTO;
import com.Clinica.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientOutputDTO patientToPatientOutputDTO(Patient patient);
    Patient patientInputDTOToPatient(PatientInputDTO patientInputDto);
}
