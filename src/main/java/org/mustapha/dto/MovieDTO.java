package org.mustapha.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

//@Data
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

    public MovieDTO() {
    }

    public MovieDTO(Long id, String title, int publishedYear, Double duration, Long directorId, Long categoryId) {
        this.id = id;
        this.title = title;
        this.publishedYear = publishedYear;
        this.duration = duration;
        this.directorId = directorId;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
