package org.mustapha.repository;

import org.mustapha.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
//    @Query("SELECT d FROM Director d WHERE d.firstName LIKE %:name% OR d.lastName LIKE %:name%")
//    List<Director> findByNameContaining(@Param("name") String name);
//    List<Director> findByNationality(String nationality);
}