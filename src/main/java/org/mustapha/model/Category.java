package org.mustapha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(length = 500)
    private String description;

    // The mappedBy value MUST match the field name in Movie class
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movieList = new ArrayList<>();

    // Helper methods
    public void addMovie(Movie movie) {
        movieList.add(movie);
        movie.setCategory(this);
    }

    public void removeMovie(Movie movie) {
        movieList.remove(movie);
        movie.setCategory(null);
    }
}