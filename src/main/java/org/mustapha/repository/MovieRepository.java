package org.mustapha.repository;

import org.mustapha.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

//    List<Film> findByTitleContaining(String keyword);
//    List<Film> findByReleaseYear(int year);
//
//    // CORRECTION : Utilisez @Query pour les relations
//    @Query("SELECT f FROM Film f WHERE f.director.idDirector = :directorId")
//    List<Film> findByDirectorId(@Param("directorId") Long directorId);
//
//    @Query("SELECT f FROM Film f WHERE f.category.idCategory = :categoryId")
//    List<Film> findByCategoryId(@Param("categoryId") Long categoryId);
//
//    List<Film> findByRatingGreaterThanEqual(Double rating);

    List<Movie> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT m FROM Movie m WHERE FUNCTION('YEAR', m.releaseDate) = :year")
    List<Movie> findByReleaseYear(@Param("year") int year);

    List<Movie> findByRatingGreaterThanEqual(double rating);


    List<Movie> findByCategory_Id(Long categoryId);

}