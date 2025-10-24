package org.mustapha.service;

import org.mustapha.dto.DirectorDTO;
import org.mustapha.dto.MovieDTO;

import java.util.List;

public interface DirectorService {
    DirectorDTO save(DirectorDTO dto);
    DirectorDTO update(DirectorDTO dto);
    void delete(Long id);
    DirectorDTO findById(Long id);
    List<DirectorDTO> findAll();
    List<DirectorDTO> searchByFirstName(String firstName);

    List<MovieDTO> findMoviesByDirector(Long directorId);

}