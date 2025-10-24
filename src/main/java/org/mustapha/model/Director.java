package org.mustapha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directors")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String nationality;

    @Column(length = 1000)
    private String biography;

    // The mappedBy value MUST match the field name in Movie class
    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movieList = new ArrayList<>();

    public Director(){}
    public Director(Long id, String name, String nationality, String biography, List<Movie> movieList) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.biography = biography;
        this.movieList = movieList;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}