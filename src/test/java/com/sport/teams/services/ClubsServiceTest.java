package com.sport.teams.services;

import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.entitys.clubs.ClubRequest;
import com.sport.teams.exceptionHandlers.BadRequestException;
import com.sport.teams.exceptionHandlers.NoContent;
import com.sport.teams.exceptionHandlers.NotFoundException;
import com.sport.teams.repositories.ClubsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
public class ClubsServiceTest {
    @Mock
    private ClubsRepository clubsRepository;

    @InjectMocks
    private ClubsService clubsService;

    public ClubsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
    
    private List<Club> mockList() {
        Club club1 = new Club();
        club1.setNombre("FC Barcelona");
        Club club2 = new Club();
        club2.setNombre("Real Madrid");
       return Arrays.asList(club1, club2);
    }
    
    @Test
    public void testFindAll() {
        // Arrange
        List<Club> clubs = this.mockList();
        when(clubsRepository.findAll()).thenReturn(clubs);

        // Act
        List<Club> result = clubsService.findAll();

        // Assert
        assertEquals(clubs, result);
        assertEquals(clubs.size(), result.size());
    }

    @Test
    public void testFindAll_NoContent() {
        // Arrange
        when(clubsRepository.findAll()).thenReturn(Arrays.asList());

        // Act & Assert
        assertThrows(NoContent.class, () -> clubsService.findAll());
    }

    @Test
    public void testFindById() {
        // Arrange
        Club club = new Club();
        club.setNombre("FC Barcelona");
        when(clubsRepository.findById(1L)).thenReturn(Optional.of(club));

        // Act
        Club result = clubsService.findById(1L);

        // Assert
        assertEquals(club, result);
    }

    @Test
    public void testFindById_NotFound() {
        // Arrange
        when(clubsRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> clubsService.findById(1L));
    }


    @Test
    public void testFindByNombre() {
        // Arrange
        List<Club> clubs = this.mockList();
        when(clubsRepository.findByNombreContaining(anyString())).thenReturn(clubs);

        // Act
        List<Club> result = clubsService.findByNombre("Barcelona");

        // Assert
        assertEquals(clubs, result);
        assertEquals(clubs.size(), result.size());
    }

    @Test
    public void testFindByNombre_NoContent() {
        // Arrange
        when(clubsRepository.findByNombreContaining(anyString())).thenReturn(Arrays.asList());

        // Act & Assert
        assertThrows(NoContent.class, () -> clubsService.findByNombre("Nonexistent"));
    }
    
    @Test
    public void testCreateClub() {
        // Arrange
        ClubRequest clubRequest = new ClubRequest();
        clubRequest.setNombre("FC Barcelona");
        clubRequest.setLiga("La Liga");
        clubRequest.setPais("España");
        Club club = new Club(clubRequest);
        when(clubsRepository.findByNombre(clubRequest.getNombre())).thenReturn(Optional.empty());
        when(clubsRepository.save(club)).thenReturn(club);

        // Act
        Club result = clubsService.createClub(clubRequest);

        // Assert
        assertEquals(club, result);
    }
    
    @Test
    public void testCreateClub_inclomplteRequest() {
        // Arrange
        ClubRequest clubRequest = new ClubRequest();
        clubRequest.setNombre("FC Barcelona");
        clubRequest.setLiga("La Liga");
        Club club = new Club(clubRequest);
        when(clubsRepository.findByNombre(clubRequest.getNombre())).thenReturn(Optional.empty());
        when(clubsRepository.save(club)).thenReturn(club);
        
        // Act & Assert
        assertThrows(BadRequestException.class, () -> clubsService.createClub(clubRequest));
    }

    @Test
    public void testCreateClub_BadRequest() {
        // Arrange
        ClubRequest clubRequest = new ClubRequest();
        clubRequest.setNombre("FC Barcelona");
        Club existingClub = new Club();
        existingClub.setNombre("FC Barcelona");
        when(clubsRepository.findByNombre(clubRequest.getNombre())).thenReturn(Optional.of(existingClub));

        // Act & Assert
        assertThrows(BadRequestException.class, () -> clubsService.createClub(clubRequest));
    }

    @Test
    public void testUpdateClub() {
        // Arrange
        ClubRequest clubRequest = new ClubRequest();
        clubRequest.setNombre("FC Barcelona");
        Club club = new Club(clubRequest, 1L);
        when(clubsRepository.findById(1L)).thenReturn(Optional.of(new Club()));
        when(clubsRepository.save(club)).thenReturn(club);

        // Act
        Club result = clubsService.updateClub(1L, clubRequest);

        // Assert
        assertEquals(club, result);
    }

    @Test
    public void testUpdateClub_NotFound() {
        // Arrange
        ClubRequest clubRequest = new ClubRequest();
        clubRequest.setNombre("FC Barcelona");
        when(clubsRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> clubsService.updateClub(1L, clubRequest));
    }

    @Test
    public void testDeleteClub() {
        // Arrange
        when(clubsRepository.findById(1L)).thenReturn(Optional.of(new Club()));

        // Act
        clubsService.deleteClub(1L);

        // Assert
        verify(clubsRepository).deleteById(1L);
    }

    @Test
    public void testDeleteClub_NotFound() {
        // Arrange
        when(clubsRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> clubsService.deleteClub(1L));
    }

}
