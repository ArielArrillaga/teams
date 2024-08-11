package com.sport.teams.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.exceptionHandlers.NoContent;
import com.sport.teams.exceptionHandlers.NotFoundException;
import com.sport.teams.repositories.ClubsRepository;

@Service
public class ClubsService implements IClubsService {
	private static final Logger log = LoggerFactory.getLogger(ClubsService.class);

	@Autowired
	ClubsRepository clubsRepository;
	
	/**
	 * Devuelve todos los clubes encontrados en la base de datos
	 */
	@Override
	public List<Club> findAll() {
	     List<Club> clubes = clubsRepository.findAll();
	     
	     if (clubes.isEmpty()) {
	    	 log.info("ClubsService: findAll: No hay clubes en la base de datos.");
	    	 throw new NoContent();
	     }
	     
	     return clubes;
	}
	
	/**
	 * obtiene un equipo segun su id, devuelve not found si el id no existe
	 */
	@Override
	public Club findById(Long id) {
	     return clubsRepository.findById(id)
	    		.orElseThrow(() -> { 
	    	 		log.error("ClubsService: findById: Error, no se encontro equipo con id: "+id);
	    	 		return new NotFoundException("Equipo no encontrado");
	     		});
	}
	
	/**
	 * Busca los clubes que contengan en el nombre el el parametro ingresado
	 */
	@Override
	public List<Club> findByNombre(String nombre) {
		
		 List<Club> clubes =  clubsRepository.findByNombreContaining(nombre);
		 
		 if (clubes.isEmpty()) {
	    	 log.info("ClubsService: findByNombre: No hay clubes que coincian con la busqueda.");
	    	 throw new NoContent();
	     }
	     
	     return clubes;      
    }

}
