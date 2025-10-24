package org.mustapha.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mustapha.dto.CategoryDTO;
import org.mustapha.mapper.CategoryMapper;
import org.mustapha.model.Category;
import org.mustapha.repository.CategoryRepository;

import java.util.Optional;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void testCreateCategory_Success() {
        // Arrange
        CategoryDTO dto = new CategoryDTO();
        dto.setName("Action");

        Category category = new Category();
        category.setName("Action");

        when(categoryMapper.toEntity(dto)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDTO(category)).thenReturn(dto);

        // Act
        CategoryDTO result = categoryService.save(dto);

        // Assert
        assertEquals("Action", result.getName());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void testDeleteCategory_Success() {
        // Arrange
        Long categoryId = 1L;
        doNothing().when(categoryRepository).deleteById(categoryId);

        // Act
        categoryService.delete(categoryId);

        // Assert
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    void testFindById_NotFound() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.findById(99L);
        });

        assertTrue(exception.getMessage().contains("Category not found"));
    }
}
