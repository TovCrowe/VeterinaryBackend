
package com.vetproject.vet.DTO;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientUpdateDTO {
    private String nombre;
    private String email;
    
    private String propietario;
    private Date fechaDeAlta;
    private String sintomas;
}