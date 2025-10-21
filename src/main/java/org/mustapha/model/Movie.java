package org.mustapha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor // provide no arg constructor
@AllArgsConstructor // provide constructor with all fields

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int publishedYear;
    private Double duration;

//    relations
    /**
     * each director can have many movies
     */
    @ManyToOne()
    @JoinColumn(name = "director_id")
    private Director director;


    /**
     * many movies has only one category
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



}
