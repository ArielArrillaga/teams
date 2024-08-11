package com.sport.teams.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.exceptionHandlers.NoContent;
import com.sport.teams.repositories.ClubsRepository;

@Service
public class clubsService implements IClubsService {
	private static final Logger log = LoggerFactory.getLogger(clubsService.class);

	@Autowired
	ClubsRepository clubsRepository;
	
	/**
	 * Devuelve todos los clubes encontrados en la base de datos
	 */
	@Override
	public List<Club> findAll() {
	     List<Club> clubes = clubsRepository.findAll();
	     
	     if (clubes.isEmpty()) {
	    	 log.info("clubsService: findAll: No hay clubes en la base de datos.");
	    	 throw new NoContent();
	     }
	     
	     return clubes;
	}

}
