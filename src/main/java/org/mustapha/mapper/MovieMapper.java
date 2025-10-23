package org.mustapha.mapper;

import org.mustapha.dto.MovieDTO;
import org.mustapha.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDTO toDTO(Movie movie);

    Movie toEntity(MovieDTO movieDTO);
}