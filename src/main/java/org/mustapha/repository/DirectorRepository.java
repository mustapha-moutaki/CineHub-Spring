package org.mustapha.repository;

import org.mustapha.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
//    @Query("SELECT d FROM Director d WHERE d.firstName LIKE %:name% OR d.lastName LIKE %:name%")
//    List<Director> findByNameContaining(@Param("name") String name);
//    List<Director> findByNationality(String nationality);
List<Director> findByFirstNameContainingIgnoreCase(String firstName);
    @Query("SELECT d FROM Director d LEFT JOIN FETCH d.movieList WHERE d.id = :id")
    Optional<Director> findByIdWithMovies(@Param("id") Long id);

}