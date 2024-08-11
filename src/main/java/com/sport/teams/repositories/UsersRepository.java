package com.sport.teams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sport.teams.entitys.security.UserApp;

@Repository
public interface UsersRepository extends JpaRepository<UserApp, Long> {
    
    // Método para encontrar un usuario por su nombre de usuario
    public UserApp findByUsername(String username);
}
