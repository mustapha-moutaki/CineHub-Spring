    package org.mustapha.controller;

    import jakarta.validation.Valid;
    import org.mustapha.dto.MovieDTO;
    import org.mustapha.service.MovieService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/movies")
    public class MovieController {

        private final MovieService movieService;

        public MovieController(MovieService movieService) {
            this.movieService = movieService;
        }

        // Add new movie
        @PostMapping
        public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieDTO movieDTO) {
            MovieDTO saved = movieService.save(movieDTO);
            return ResponseEntity.ok(saved);
        }

        // Update movie
        @PutMapping("/{id}")
        public ResponseEntity<MovieDTO> updateMovie(
                @PathVariable("id") Long id,
                @Valid @RequestBody MovieDTO movieDTO) {

            movieDTO.setId(id);
            MovieDTO updated = movieService.update(movieDTO);
            return ResponseEntity.ok(updated);
        }

        // Delete movie
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id) {
            movieService.delete(id);
            return ResponseEntity.noContent().build();
        }

        // Get movie by ID
        @GetMapping("/{id}")
        public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") Long id) {
            MovieDTO movie = movieService.findById(id);
            return ResponseEntity.ok(movie);
        }

        // Get all movies
        @GetMapping
        public ResponseEntity<List<MovieDTO>> getAllMovies() {
            List<MovieDTO> movies = movieService.findAll();
            return ResponseEntity.ok(movies);
        }

        // serach by title
        @GetMapping("/search")
        public ResponseEntity<List<MovieDTO>> searchMoviesByTitle(@RequestParam("title") String title) {
            System.out.println("Searching for: " + title);
            List<MovieDTO> movies = movieService.searchByTitle(title);
            return ResponseEntity.ok(movies);
        }

        // filter by the year
        @GetMapping("/filter/byYear")
        public ResponseEntity<List<MovieDTO>> filterMoviesByYear(@RequestParam("year") int year) {
            List<MovieDTO> movies = movieService.filterByYear(year);
            return ResponseEntity.ok(movies);
        }

        @GetMapping("/filter/byMinRating")
        public ResponseEntity<List<MovieDTO>> filterMoviesByMinRating(@RequestParam("rating") double rating) {
            List<MovieDTO> movies = movieService.filterByMinRating(rating);
            return ResponseEntity.ok(movies);
        }


    }
