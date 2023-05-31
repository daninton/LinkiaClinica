package com.Clinica.service;

import com.Clinica.Controller.DTO.AppointmentInputDTO;
import com.Clinica.Controller.DTO.AppointmentOutputDTO;
import com.Clinica.Controller.DTO.PatientOutputDTO;
import com.Clinica.entity.Appointment;
import com.Clinica.entity.Patient;
import com.Clinica.respository.AppointmentRepository;
import com.Clinica.respository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<AppointmentOutputDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(this::convertToAppointmentOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentOutputDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return convertToAppointmentOutputDTO(appointment);
    }

    @Override
    public AppointmentOutputDTO addAppointment(AppointmentInputDTO appointmentInputDto) {
        Appointment appointment = convertToAppointment(appointmentInputDto);
        List<Patient> patients = patientRepository.findAllById(appointmentInputDto.getPatientIds());
        appointment.setPatients(patients);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToAppointmentOutputDTO(savedAppointment);
    }

    @Override
    public AppointmentOutputDTO updateAppointment(Long id, AppointmentInputDTO appointmentInputDto) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        List<Patient> patients = patientRepository.findAllById(appointmentInputDto.getPatientIds());
        existingAppointment.setPatients(patients);
        existingAppointment.setAppointmentTime(appointmentInputDto.getAppointmentTime());

        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
        return convertToAppointmentOutputDTO(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    private AppointmentOutputDTO convertToAppointmentOutputDTO(Appointment appointment) {
        AppointmentOutputDTO outputDTO = new AppointmentOutputDTO();
        outputDTO.setId(appointment.getId());
        outputDTO.setAppointmentTime(appointment.getAppointmentTime());
        outputDTO.setPatients(convertToPatientOutputDTOs(appointment.getPatients()));
        return outputDTO;
    }

    private List<AppointmentOutputDTO> convertToAppointmentOutputDTOs(List<Appointment> appointments) {
        return appointments.stream()
                .map(this::convertToAppointmentOutputDTO)
                .collect(Collectors.toList());
    }

    private Appointment convertToAppointment(AppointmentInputDTO appointmentInputDto) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(appointmentInputDto.getAppointmentTime());
        return appointment;
    }

    private List<PatientOutputDTO> convertToPatientOutputDTOs(List<Patient> patients) {
        return patients.stream()
                .map(this::convertToPatientOutputDTO)
                .collect(Collectors.toList());
    }

    private PatientOutputDTO convertToPatientOutputDTO(Patient patient) {
        PatientOutputDTO outputDTO = new PatientOutputDTO();
        outputDTO.setId(patient.getId());
        outputDTO.setName(patient.getName());
        outputDTO.setLastName(patient.getLastName());
        outputDTO.setAge(patient.getAge());
        return outputDTO;
    }
}
