
package org.mustapha.mapper;

import org.mustapha.dto.MovieDTO;
import org.mustapha.model.Movie;
import org.mustapha.model.Director;
import org.mustapha.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(source = "director.id", target = "directorId")
    @Mapping(source = "category.id", target = "categoryId")
    MovieDTO toDTO(Movie movie);

    @Mapping(source = "directorId", target = "director", qualifiedByName = "mapDirector")
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategory")
    Movie toEntity(MovieDTO dto);

    @Named("mapDirector")
    default Director mapDirector(Long id){
        if(id == null) return null;
        Director director = new Director();
        director.setId(id);
        return director;
    }

    @Named("mapCategory")
    default Category mapCategory(Long id){
        if(id == null) return null;
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
