package com.Clinica.service;

import com.Clinica.Controller.DTO.AppointmentInputDTO;
import com.Clinica.Controller.DTO.AppointmentOutputDTO;

import java.util.List;

public interface AppointmentService {
    List<AppointmentOutputDTO> getAllAppointments();
    AppointmentOutputDTO getAppointmentById(Long id);
    AppointmentOutputDTO addAppointment(AppointmentInputDTO appointmentInputDto);
    AppointmentOutputDTO updateAppointment(Long id, AppointmentInputDTO appointmentInputDto);
    void deleteAppointment(Long id);
}
