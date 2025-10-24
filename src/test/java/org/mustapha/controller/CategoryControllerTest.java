package org.mustapha.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mustapha.dto.CategoryDTO;
import org.mustapha.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void shouldCreateCategorySuccessfully() {
        // Given
        when(categoryService.save(any(CategoryDTO.class))).thenAnswer(invocation -> {
            CategoryDTO input = invocation.getArgument(0);
            CategoryDTO saved = new CategoryDTO();
            saved.setId(1L);
            saved.setName(input.getName());
            saved.setDescription(input.getDescription());
            return saved;
        });

        // When
        CategoryDTO inputDTO = new CategoryDTO();
        inputDTO.setName("Action Movies");
        inputDTO.setDescription("Exciting action-packed movies");

        ResponseEntity<CategoryDTO> response = categoryController.createCategory(inputDTO);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getName()).isEqualTo("Action Movies");
    }
}