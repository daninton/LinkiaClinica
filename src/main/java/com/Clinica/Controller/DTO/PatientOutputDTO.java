package com.Clinica.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientOutputDTO {
    private Long id;
    private String name;
    private String lastName;
    private Integer age;
}
