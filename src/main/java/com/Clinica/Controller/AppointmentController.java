package com.Clinica.Controller;

import com.Clinica.Controller.DTO.AppointmentInputDTO;
import com.Clinica.Controller.DTO.AppointmentOutputDTO;
import com.Clinica.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentOutputDTO>> getAllAppointments() {
        List<AppointmentOutputDTO> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentOutputDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentOutputDTO appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping
    public ResponseEntity<AppointmentOutputDTO> addAppointment(@RequestBody AppointmentInputDTO appointmentInputDto) {
        AppointmentOutputDTO savedAppointment = appointmentService.addAppointment(appointmentInputDto);
        return ResponseEntity.ok(savedAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentOutputDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentInputDTO appointmentInputDto) {
        AppointmentOutputDTO updatedAppointment = appointmentService.updateAppointment(id, appointmentInputDto);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
