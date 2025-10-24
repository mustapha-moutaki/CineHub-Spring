package org.mustapha.repository;

import org.mustapha.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}