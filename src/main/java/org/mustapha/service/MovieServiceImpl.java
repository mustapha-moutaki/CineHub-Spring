package org.mustapha.service;

import org.mustapha.dto.MovieDTO;
import org.mustapha.mapper.MovieMapper;
import org.mustapha.model.Movie;
import org.mustapha.repository.MovieRepository;
import org.mustapha.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public MovieDTO save(MovieDTO dto) {
        Movie movie = movieMapper.toEntity(dto);
        Movie saved = movieRepository.save(movie);
        return movieMapper.toDTO(saved);
    }

    @Override
    public MovieDTO update(MovieDTO dto) {
        Movie existing = movieRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + dto.getId()));


        existing.setTitle(dto.getTitle());
        existing.setDuration(dto.getDuration() != null ? dto.getDuration().intValue() : null);

        existing.setDirector(movieMapper.mapDirector(dto.getDirectorId()));
        existing.setCategory(movieMapper.mapCategory(dto.getCategoryId()));

        Movie updated = movieRepository.save(existing);
        return movieMapper.toDTO(updated);
    }


    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO findById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        return movieMapper.toDTO(movie);
    }

    @Override
    public List<MovieDTO> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> filterByYear(int year) {
        return movieRepository.findByReleaseYear(year)
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> filterByMinRating(double rating) {
        return movieRepository.findByRatingGreaterThanEqual(rating)
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findMoviesByCategory(Long categoryId) {
        List<Movie> movies = movieRepository.findByCategory_Id(categoryId);
        return movies.stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

}