package org.mustapha.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mustapha.dto.MovieDTO;
import org.mustapha.mapper.MovieMapper;
import org.mustapha.model.Movie;
import org.mustapha.repository.MovieRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    void testCreateMovie_Success() {
        // Arrange
        MovieDTO dto = new MovieDTO();
        dto.setTitle("Iron Man");

        Movie movie = new Movie();
        movie.setTitle("Iron Man");

        when(movieMapper.toEntity(dto)).thenReturn(movie);
        when(movieRepository.save(movie)).thenReturn(movie);
        when(movieMapper.toDTO(movie)).thenReturn(dto);

        // Act
        MovieDTO result = movieService.save(dto);

        // Assert
        assertEquals("Iron Man", result.getTitle());
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    void testDeleteMovie_Success() {
        // Arrange
        Long movieId = 1L;
        doNothing().when(movieRepository).deleteById(movieId);

        // Act
        movieService.delete(movieId);

        // Assert
        verify(movieRepository, times(1)).deleteById(movieId);
    }

    @Test
    void testFindById_NotFound() {
        when(movieRepository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            movieService.findById(99L);
        });

        assertTrue(exception.getMessage().contains("Movie not found"));
    }
}
