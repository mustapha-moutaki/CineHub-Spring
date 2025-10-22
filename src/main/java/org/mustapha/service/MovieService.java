package org.mustapha.service;

import org.mustapha.dto.MovieDTO;
import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto);

    MovieDTO update(MovieDTO dto);

    void delete(Long id);

    MovieDTO findById(Long id);

    List<MovieDTO> findAll();
}
