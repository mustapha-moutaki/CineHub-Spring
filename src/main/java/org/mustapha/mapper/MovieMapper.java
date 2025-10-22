//package org.mustapha.mapper;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mustapha.dto.MovieDTO;
//import org.mustapha.model.Category;
//import org.mustapha.model.Director;
//import org.mustapha.model.Movie;
//@Mapper(componentModel = "spring")
//public interface MovieMapper {
//
//    @Mapping(source = "directorId", target = "director")
//    @Mapping(source = "categoryId", target = "category")
//    Movie toEntity(MovieDTO dto);
//
//    @Mapping(source = "director.id", target = "directorId")
//    @Mapping(source = "category.id", target = "categoryId")
//    MovieDTO toDTO(Movie movie);
//
//    // Helper methods for MapStruct to convert IDs to entities
//    default Director mapDirector(Long id) {
//        if (id == null) return null;
//        Director d = new Director();
//        d.setId(id);
//        return d;
//    }
//
//    default Category mapCategory(Long id) {
//        if (id == null) return null;
//        Category c = new Category();
//        c.setId(id);
//        return c;
//    }
//}
