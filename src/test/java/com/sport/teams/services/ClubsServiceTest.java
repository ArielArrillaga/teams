package com.sport.teams.services;

import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.exceptionHandlers.NoContent;
import com.sport.teams.repositories.ClubsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    public void testFindByNombre() {
        // Arrange
        Club club1 = new Club();
        club1.setNombre("FC Barcelona");
        Club club2 = new Club();
        club2.setNombre("Real Madrid");
        List<Club> clubs = Arrays.asList(club1, club2);
        when(clubsRepository.findByNombreContaining(anyString())).thenReturn(clubs);

        // Act
        List<Club> result = clubsService.findByNombre("Barcelona");

        // Assert
        assertEquals(clubs, result);
        assertEquals(clubs.size(), result.size());
        assertTrue(result.contains(club1));
        assertTrue(result.contains(club2));
    }

    @Test
    public void testFindByNombre_NoContent() {
        // Arrange
        when(clubsRepository.findByNombreContaining(anyString())).thenReturn(Arrays.asList());

        // Act & Assert
        assertThrows(NoContent.class, () -> clubsService.findByNombre("Nonexistent"));
    }
}
