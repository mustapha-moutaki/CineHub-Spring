package org.mustapha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directors")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // Helper methods
    public void addMovie(Movie movie) {
        movieList.add(movie);
        movie.setDirector(this);
    }

    public void removeMovie(Movie movie) {
        movieList.remove(movie);
        movie.setDirector(null);
    }
}