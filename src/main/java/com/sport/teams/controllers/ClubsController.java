package com.sport.teams.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sport.teams.anotations.ValidateJwt;
import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.entitys.clubs.ClubRequest;
import com.sport.teams.services.IClubsService;

@RestController
@RequestMapping("/equipos")
public class ClubsController implements IClubsController {
	private static final Logger log = LoggerFactory.getLogger(ClubsController.class);

	@Autowired
	IClubsService clubsService;
	
	@Override
	@GetMapping("/")
	@ValidateJwt
	public ResponseEntity<List<Club>> findAll() {
		log.info("ClubsController: findAll: Iniciando servicio.");
		return new ResponseEntity<List<Club>> (clubsService.findAll(), HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/{id}")
	@ValidateJwt
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
		log.info("ClubsController: getClubById: Iniciando servicio, id: "+id);
        return new ResponseEntity<Club> (clubsService.findById(id), HttpStatus.OK);
    }
	
	@Override
    @GetMapping("/buscar")
	@ValidateJwt
    public ResponseEntity<List<Club>> getClubsByNombre(@RequestParam String nombre) {
		log.info("ClubsController: getClubsByNombre: Iniciando servicio, buscando clubes que contengan " +nombre+ " en su nombre.");
        return new ResponseEntity<List<Club>> (clubsService.findByNombre(nombre), HttpStatus.OK);
    }
	
	@Override
	@PostMapping("/")
	@ValidateJwt
    public ResponseEntity<Club> createClub(@RequestBody ClubRequest club) {
		log.info("ClubsController: getClubsByNombre: Iniciando servicio, creando club "+ club.getNombre());
        return new ResponseEntity<Club>(clubsService.createClub(club), HttpStatus.CREATED);
    }
	
	@Override
	@PutMapping("/{id}")
	@ValidateJwt
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody ClubRequest club) {
		log.info("ClubsController: getClubsByNombre: Iniciando servicio, modificando club con id: "+ id);
        return new ResponseEntity<Club>(clubsService.updateClub(id, club), HttpStatus.OK);
    }
	
	@Override
	@DeleteMapping("/{id}")
	@ValidateJwt
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
		log.info("ClubsController: deleteClub: Iniciando servicio, id: "+id);
		clubsService.deleteClub(id);
        return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
    }

}
