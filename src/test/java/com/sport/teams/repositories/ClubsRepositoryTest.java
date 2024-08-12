package com.sport.teams.repositories;

import com.sport.teams.entitys.clubs.Club;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
public class ClubsRepositoryTest {
    @Mock
    private ClubsRepository clubsRepository;

    public ClubsRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByNombreContaining() {
        // Arrange
        Club club1 = new Club();
        club1.setNombre("FC Barcelona");
        Club club2 = new Club();
        club2.setNombre("Real Madrid");
        List<Club> clubs = Arrays.asList(club1, club2);
        when(clubsRepository.findByNombreContaining(anyString())).thenReturn(clubs);

        // Act
        List<Club> result = clubsRepository.findByNombreContaining("Barcelona");

        // Assert
        assertEquals(clubs, result);
    }

    @Test
    public void testFindByNombre() {
        // Arrange
        Club club = new Club();
        club.setNombre("FC Barcelona");
        when(clubsRepository.findByNombre(anyString())).thenReturn(Optional.of(club));

        // Act
        Optional<Club> result = clubsRepository.findByNombre("FC Barcelona");

        // Assert
        assertEquals(Optional.of(club), result);
    }
}

