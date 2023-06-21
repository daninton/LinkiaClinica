package com.Clinica.service;

import com.Clinica.Controller.DTO.PatientInputDTO;
import com.Clinica.Controller.DTO.PatientOutputDTO;
import com.Clinica.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "age", target = "age")
    PatientOutputDTO patientToPatientOutputDTO(Patient patient);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "age", target = "age")
    Patient patientInputDTOToPatient(PatientInputDTO patientInputDto);
}