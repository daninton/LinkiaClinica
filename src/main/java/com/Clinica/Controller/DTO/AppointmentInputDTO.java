package com.Clinica.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentInputDTO {
    private LocalDateTime appointmentTime;
    private List<Long> patientIds;
}