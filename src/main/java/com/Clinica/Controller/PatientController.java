package com.Clinica.Controller;

import com.Clinica.Controller.DTO.PatientInputDTO;
import com.Clinica.Controller.DTO.PatientOutputDTO;
import com.Clinica.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientOutputDTO>> getAllPatients() {
        List<PatientOutputDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientOutputDTO> getPatientById(@PathVariable Long id) {
        PatientOutputDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<PatientOutputDTO> addPatient(@RequestBody PatientInputDTO patientInputDto) {
        PatientOutputDTO savedPatient = patientService.addPatient(patientInputDto);
        return ResponseEntity.ok(savedPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientOutputDTO> updatePatient(@PathVariable Long id, @RequestBody PatientInputDTO patientInputDto) {
        PatientOutputDTO updatedPatient = patientService.updatePatient(id, patientInputDto);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
