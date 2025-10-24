package org.mustapha.controller;

import jakarta.validation.Valid;
import org.mustapha.dto.DirectorDTO;
import org.mustapha.service.DirectorService;
import org.mustapha.utilis.InputValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    //  Add new director
    @PostMapping
    public ResponseEntity<?> createDirector(@Valid @RequestBody DirectorDTO directorDTO, BindingResult result) {
        if(result.hasErrors()){
            return  ResponseEntity.badRequest().body(InputValidation.getValidationErrors(result));
        }
        DirectorDTO saved = directorService.save(directorDTO);
        return ResponseEntity.ok(saved);
    }

    // Update director
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDirector(@PathVariable("id") Long id, @Valid @RequestBody DirectorDTO directorDTO, BindingResult result) {
        if(result.hasErrors()){
            return  ResponseEntity.badRequest().body(InputValidation.getValidationErrors(result));
        }
        directorDTO.setId(id);
        DirectorDTO updated = directorService.update(directorDTO);
        return ResponseEntity.ok(updated);
    }

    // Delete director
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable("id") Long id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Get director by ID
    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable("id") Long id) {
        DirectorDTO director = directorService.findById(id);
        return ResponseEntity.ok(director);
    }

    // Get all directors
    @GetMapping
    public ResponseEntity<List<DirectorDTO>> getAllDirectors() {
        List<DirectorDTO> directors = directorService.findAll();
        return ResponseEntity.ok(directors);
    }
}
