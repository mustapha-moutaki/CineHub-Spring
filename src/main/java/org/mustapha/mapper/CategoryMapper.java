package org.mustapha.mapper;

import org.mustapha.dto.CategoryDTO;
import org.mustapha.model.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO dto = new CategoryDTO();
        dto.setIdCategory(category.getIdCategory());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    public static Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setIdCategory(dto.getIdCategory()); // May be null for new entities
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    public static void updateEntityFromDTO(CategoryDTO dto, Category entity) {
        if (dto == null || entity == null) {
            return;
        }
        // idCategory is typically not updated via DTO unless it's a specific scenario
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
    }
}