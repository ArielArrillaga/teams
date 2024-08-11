package com.sport.teams.services;

import java.util.List;

import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.entitys.clubs.ClubRequest;

public interface IClubsService {
	public List<Club> findAll();
	public Club findById(Long id);
	public List<Club> findByNombre(String nombre);
	public Club createClub(ClubRequest club);
	public Club updateClub(Long id, ClubRequest clubRequest);
}
