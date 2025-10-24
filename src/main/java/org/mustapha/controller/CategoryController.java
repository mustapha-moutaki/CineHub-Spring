package org.mustapha.controller;

import jakarta.validation.Valid;
import org.mustapha.dto.CategoryDTO;
import org.mustapha.service.CategoryService;
import org.mustapha.utilis.InputValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO ,BindingResult result) {

        if(result.hasErrors()){
            return  ResponseEntity.badRequest().body(InputValidation.getValidationErrors(result));
        }

        CategoryDTO saved = categoryService.save(categoryDTO);
        return ResponseEntity.ok(saved);
    }

//    update category
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id")  Long id, @Valid @RequestBody CategoryDTO dto,BindingResult result) {
        if(result.hasErrors()){
            return  ResponseEntity.badRequest().body(InputValidation.getValidationErrors(result));
        }
        dto.setId(id);
        CategoryDTO updated = categoryService.update(dto);
        return ResponseEntity.ok(updated);
    }

    // delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id")  Long id) {

        CategoryDTO category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    // get all
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    // search by name
    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> searchCategoriesByName(@RequestParam("name") String name) {
        List<CategoryDTO> categories = categoryService.searchByName(name);
        return ResponseEntity.ok(categories);
    }

}
