package org.mustapha.dto;

import jakarta.validation.constraints.*;

public class CategoryDTO {

    private Long id;

    @NotBlank(message = "The name is required")
    @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters")
    private String name;

    @Size(max = 255, message = "The description cannot exceed 255 characters")
    private String description;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
