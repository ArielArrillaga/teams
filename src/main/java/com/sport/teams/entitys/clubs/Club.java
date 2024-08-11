package com.sport.teams.entitys.clubs;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "clubs")
@Data
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String liga;
    private String pais;
    
    public Club() {}
    
    public Club(ClubRequest request) {
    	this.nombre = request.getNombre();
    	this.liga = request.getLiga();
    	this.pais = request.getPais();
    }
    
}
