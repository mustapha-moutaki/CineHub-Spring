//package org.mustapha.service.Impl;
//
//import org.mustapha.dto.MovieDTO;
//import org.mustapha.mapper.MovieMapper;
//import org.mustapha.model.Category;
//import org.mustapha.model.Director;
//import org.mustapha.model.Movie;
//import org.mustapha.repositlry.MovieRepository;
//import org.mustapha.service.MovieService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class MovieServiceImpl implements MovieService {
//
//    @Autowired
//    private MovieRepository movieRepository;
//
////    @Autowired
////    private DirectorRepository directorRepository;
////
////    @Autowired
////    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private MovieMapper movieMapper;
//
//    @Override
//    public MovieDTO save(MovieDTO dto) {
//        // get director and category from db
////        Director director = directorRepository.findById(dto.getDirectorId());
////        Category category = categoryRepository.findById(dto.getCategoryId());
//
//        // convert DTO to Entity
//        Movie movie = movieMapper.toEntity(dto);
//
////        movie.setDirector(director);
////        movie.setCategory(category);
//
//        // save movie
//        movieRepository.save(movie);
//
//        // convert Entity to Dto to return it to the interface
//        return movieMapper.toDTO(movie);
//    }
//
//    @Override
//    public MovieDTO update(MovieDTO dto) {
//        //get existed movie
//        Movie existingMovie = movieRepository.findById(dto.getId());
//        if (existingMovie == null) {
//            throw new RuntimeException("Movie not found with id: " + dto.getId());
//        }
//
//        // get director and categoru form db
////        Director director = directorRepository.findById(dto.getDirectorId());
////        Category category = categoryRepository.findById(dto.getCategoryId());
//
//        // convert DTO to Entity
//        Movie movieToUpdate = movieMapper.toEntity(dto);
//
//
//        movieToUpdate.setId(existingMovie.getId()); // important to update
////        movieToUpdate.setDirector(director);
////        movieToUpdate.setCategory(category);
//
//        //dave upate
//        movieRepository.save(movieToUpdate);
//
//        //Convert Entity to DTO and return it to ui
//        return movieMapper.toDTO(movieToUpdate);
//    }
//
//
//    @Override
//    public void delete(Long id) {
//        movieRepository.delete(id);
//    }
//
//
//    @Override
//    public MovieDTO findById(Long id) {
//        Movie movie = movieRepository.findById(id);
//        return movieMapper.toDTO(movie);
//    }
//
//    @Override
//    public List<MovieDTO> findAll() {
//        return movieRepository.findAll()
//                .stream()
//                .map(movieMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//
//
//
//
//
//
//}
