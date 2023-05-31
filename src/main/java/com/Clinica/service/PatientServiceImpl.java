package com.Clinica.service;

import com.Clinica.Controller.DTO.PatientInputDTO;
import com.Clinica.Controller.DTO.PatientOutputDTO;
import com.Clinica.entity.Patient;
import com.Clinica.respository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PatientOutputDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(this::convertToPatientOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientOutputDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return convertToPatientOutputDTO(patient);
    }

    @Override
    public PatientOutputDTO addPatient(PatientInputDTO patientInputDto) {
        Patient patient = convertToPatient(patientInputDto);
        Patient savedPatient = patientRepository.save(patient);
        return convertToPatientOutputDTO(savedPatient);
    }

    @Override
    public PatientOutputDTO updatePatient(Long id, PatientInputDTO patientInputDto) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        existingPatient.setName(patientInputDto.getName());
        existingPatient.setLastName(patientInputDto.getLastName());
        existingPatient.setAge(patientInputDto.getAge());

        Patient updatedPatient = patientRepository.save(existingPatient);
        return convertToPatientOutputDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    private PatientOutputDTO convertToPatientOutputDTO(Patient patient) {
        PatientOutputDTO outputDTO = new PatientOutputDTO();
        outputDTO.setId(patient.getId());
        outputDTO.setName(patient.getName());
        outputDTO.setLastName(patient.getLastName());
        outputDTO.setAge(patient.getAge());
        return outputDTO;
    }

    private Patient convertToPatient(PatientInputDTO patientInputDto) {
        Patient patient = new Patient();
        patient.setName(patientInputDto.getName());
        patient.setLastName(patientInputDto.getLastName());
        patient.setAge(patientInputDto.getAge());
        return patient;
    }
}

