package org.mustapha.controller;

import jakarta.validation.Valid;
import org.mustapha.dto.CategoryDTO;
import org.mustapha.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
//@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // add new category
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO saved = categoryService.save(categoryDTO);
        return ResponseEntity.ok(saved);
    }

//    update category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id")  Long id, @Valid @RequestBody CategoryDTO dto) {
        dto.setId(id);
        CategoryDTO updated = categoryService.update(dto);
        return ResponseEntity.ok(updated);
    }

    // delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id")  Long id) {
        CategoryDTO category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    // get all
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }
}
