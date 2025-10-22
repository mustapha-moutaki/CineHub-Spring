package org.mustapha.mapper;

import org.mapstruct.Mapper;
import org.mustapha.dto.CategoryDTO;
import org.mustapha.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // Convert DTO to Entity
    Category toEntity(CategoryDTO dto);

    // Convert Entity to DTO
    CategoryDTO toDTO(Category category);
}
