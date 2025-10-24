package org.mustapha.service.Impl;

import org.mustapha.dto.DirectorDTO;
import org.mustapha.mapper.DirectorMapper;
import org.mustapha.model.Director;
import org.mustapha.repository.DirectorRepository;
import org.mustapha.service.DirectorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @Override
    public DirectorDTO save(DirectorDTO dto) {
        Director director = directorMapper.toEntity(dto);
        Director saved = directorRepository.save(director);
        return directorMapper.toDTO(saved);
    }

    @Override
    public DirectorDTO update(DirectorDTO dto) {
        Director existing = directorRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + dto.getId()));

        // from filed to entity
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setNationality(dto.getNationality());
        existing.setBirthDate(dto.getBirthDate());
        existing.setBiography(dto.getBiography());

        // save in db
        Director updated = directorRepository.save(existing);

        return directorMapper.toDTO(updated);
    }


    @Override
    public void delete(Long id) {
        directorRepository.deleteById(id);
    }

    @Override
    public DirectorDTO findById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + id));
        return directorMapper.toDTO(director);
    }

    @Override
    public List<DirectorDTO> findAll() {
        return directorRepository.findAll()
                .stream()
                .map(directorMapper::toDTO)
                .collect(Collectors.toList());
    }
}