package org.mustapha.serivce;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mustapha.dto.MovieDTO;
import org.mustapha.mapper.MovieMapper;
import org.mustapha.model.Category;
import org.mustapha.model.Director;
import org.mustapha.model.Movie;
import org.mustapha.repository.MovieRepository;
import org.mustapha.service.Impl.MovieServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieServiceImpl movieService;

    private MovieDTO movieDTO;
    private Movie movie;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);


        movieDTO = new MovieDTO();
        movieDTO.setTitle("Inception");
        movieDTO.setDirectorId(1L);
        movieDTO.setCategoryId(2L);
        movieDTO.setDuration(148.0);
        movieDTO.setReleaseDate(LocalDate.of(2024, 12, 12));


        movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");
        Director director = new Director();
        director.setId(1L);
        movie.setDirector(director);

        Category category = new Category();
        category.setId(2L);
        movie.setCategory(category);

        movie.setDuration(148);
        movie.setReleaseDate(LocalDate.of(2024, 12, 12));
    }

    @Test
    public void testSaveMovie() {

        when(movieMapper.toEntity(movieDTO)).thenReturn(movie);
        when(movieRepository.save(movie)).thenReturn(movie);
        when(movieMapper.toDTO(movie)).thenReturn(movieDTO);

        MovieDTO saved = movieService.save(movieDTO);

        assertEquals("Inception", saved.getTitle());
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    public void testFindById() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieMapper.toDTO(movie)).thenReturn(movieDTO);

        MovieDTO result = movieService.findById(1L);

        assertEquals("Inception", result.getTitle());
        verify(movieRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        List<Movie> movies = Arrays.asList(movie);
        when(movieRepository.findAll()).thenReturn(movies);
        when(movieMapper.toDTO(movie)).thenReturn(movieDTO);

        List<MovieDTO> result = movieService.findAll();

        assertEquals(1, result.size());
        assertEquals("Inception", result.get(0).getTitle());
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateMovie() {
        MovieDTO updatedDTO = new MovieDTO();
        updatedDTO.setId(1L);
        updatedDTO.setTitle("Inception Updated");
        updatedDTO.setDirectorId(1L);
        updatedDTO.setCategoryId(2L);
        updatedDTO.setDuration(150.0);

        Movie updatedMovie = new Movie();
        updatedMovie.setId(1L);
        updatedMovie.setTitle("Inception Updated");
        Director director = new Director();
        director.setId(1L);
        movie.setDirector(director);

        Category category = new Category();
        category.setId(2L);
        movie.setCategory(category);

        updatedMovie.setDuration(150);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any(Movie.class))).thenReturn(updatedMovie);
        when(movieMapper.toDTO(updatedMovie)).thenReturn(updatedDTO);

        MovieDTO result = movieService.update(updatedDTO);

        assertEquals("Inception Updated", result.getTitle());
        verify(movieRepository, times(1)).save(any(Movie.class));
    }

    @Test
    public void testDeleteMovie() {
        doNothing().when(movieRepository).deleteById(1L);

        movieService.delete(1L);

        verify(movieRepository, times(1)).deleteById(1L);
    }
}
