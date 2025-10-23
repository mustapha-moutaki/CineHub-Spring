package org.mustapha.service.Impl;

import org.mustapha.dto.CategoryDTO;
import org.mustapha.mapper.CategoryMapper;
import org.mustapha.model.Category;
import org.mustapha.repository.CategoryRepository;
import org.mustapha.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true) // Default to read-only transactions
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDTO> findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::toDTO);
    }

    @Override
    @Transactional // Override to enable write transaction
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toDTO(savedCategory);
    }

    @Override
    @Transactional // Override to enable write transaction
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    CategoryMapper.updateEntityFromDTO(categoryDTO, existingCategory);
                    Category updatedCategory = categoryRepository.save(existingCategory);
                    return CategoryMapper.toDTO(updatedCategory);
                })
                .orElse(null); // Or throw an exception for not found
    }

    @Override
    @Transactional // Override to enable write transaction
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}