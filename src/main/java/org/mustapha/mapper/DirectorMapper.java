package org.mustapha.mapper;

import org.mustapha.dto.DirectorDTO;
import org.mustapha.model.Director;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper INSTANCE = Mappers.getMapper(DirectorMapper.class);

    DirectorDTO toDTO(Director director);

    Director toEntity(DirectorDTO directorDTO);
}