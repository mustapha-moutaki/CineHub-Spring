package org.mustapha.service;

import org.mustapha.dto.CategoryDTO;
import org.mustapha.dto.MovieDTO;
import org.mustapha.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryDTO save(CategoryDTO dto);
    CategoryDTO update(CategoryDTO dto);
    void delete(Long id);
    CategoryDTO findById(Long id);
    List<CategoryDTO>findAll();
    List<CategoryDTO> searchByName(String name);
}
