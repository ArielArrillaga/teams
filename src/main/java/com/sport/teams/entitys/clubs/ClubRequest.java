package com.sport.teams.entitys.clubs;

import com.sport.teams.exceptionHandlers.BadRequestException;

import lombok.Data;

@Data
public class ClubRequest {
    private String nombre;
    private String liga;
    private String pais;
    
    // Método para validar atributos
    public void validateAttributes() {
        if (nombre == null || liga == null || pais == null ||
        	nombre.trim().isEmpty() || liga.trim().isEmpty() || pais.trim().isEmpty()) {
            throw new BadRequestException("La solicitud es invalida");
        }
     
    }
}
