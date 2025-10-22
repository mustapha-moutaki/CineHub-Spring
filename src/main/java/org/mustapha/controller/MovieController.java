//package org.mustapha.controller;
//
//import jakarta.validation.Valid;
//import org.mustapha.dto.MovieDTO;
//import org.mustapha.mapper.MovieMapper;
//import org.mustapha.model.Category;
//import org.mustapha.model.Director;
//import org.mustapha.model.Movie;
//import org.mustapha.service.MovieService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
////@Controller this return jsp and we use it for html view
//@RequestMapping// this return json as i need in my case
//
//public class MovieController {
//
//    private final MovieService movieService;
//
//    public MovieController(MovieService movieService){
//        this.movieService = movieService;
//    }
//
//    // create movie
//    //@Valid it's check validation in entities validations
//    @PostMapping
//    public ResponseEntity<?> createMovie(@Valid @RequestBody MovieDTO movieDTO, BindingResult result){
//        if(result.hasErrors()){
//            return ResponseEntity.badRequest().body(result.getAllErrors());
//        }
//
////         cal to return dto to entity
//        Director director = directorService.findById(movieDTO.getDirectorId());
//        Category category = categoryService.findById(movieDTO.getCategoryId());
//
//        Movie movie = MovieMapper.toEntity(movieDTO, director, category);
//        Movie saved = movieService.save(movie);
//
//
//        MovieDTO responseDTO = MovieMapper.toDTO(saved);
//
//        return ResponseEntity.ok(responseDTO);
//    }
//
//}
