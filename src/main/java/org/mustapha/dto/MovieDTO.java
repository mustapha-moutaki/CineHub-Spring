package org.mustapha.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieDTO {


    private Long id; // usefull in update

    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    private int publishedYear;

    private Double duration;

    @NotNull
    private Long directorId; // jsut the id of didrector

    @NotNull
    private Long categoryId; // jsut the id of catefory
}
