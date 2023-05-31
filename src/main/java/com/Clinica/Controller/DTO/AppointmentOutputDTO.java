package com.Clinica.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentOutputDTO {
    private Long id;
    private LocalDateTime appointmentTime;
    private List<PatientOutputDTO> patients;
}