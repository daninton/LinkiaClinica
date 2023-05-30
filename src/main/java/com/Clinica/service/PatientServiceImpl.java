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

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public List<PatientOutputDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::patientToPatientOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientOutputDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.patientToPatientOutputDTO(patient);
    }

    @Override
    @Transactional
    public PatientOutputDTO addPatient(PatientInputDTO patientInputDto) {
        Patient patient = patientMapper.patientInputDTOToPatient(patientInputDto);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.patientToPatientOutputDTO(savedPatient);
    }

    @Override
    public PatientOutputDTO updatePatient(Long id, PatientInputDTO patientInputDto) {
        Patient existingPatient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        existingPatient.setName(patientInputDto.getName());
        existingPatient.setLastName(patientInputDto.getLastName());
        existingPatient.setAge(patientInputDto.getAge());
        Patient updatedPatient = patientRepository.save(existingPatient);
        return patientMapper.patientToPatientOutputDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
