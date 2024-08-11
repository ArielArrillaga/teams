package com.sport.teams.services;

import java.util.List;

import com.sport.teams.entitys.clubs.Club;

public interface IClubsService {
	public List<Club> findAll();
	public Club findById(Long id);
	public List<Club> findByNombre(String nombre);
}
