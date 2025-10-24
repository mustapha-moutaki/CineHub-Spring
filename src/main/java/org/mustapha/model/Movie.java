package org.mustapha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column
    private Integer duration; // in minutes

    @Column(length = 50)
    private String genre;

    @Column(length = 500)
    private String posterUrl;

    // REQUIRED: Director relationship
    @ManyToOne()//fetch = FetchType.LAZY
    @JoinColumn(name = "director_id")
    private Director director;

    // REQUIRED: Category relationship
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    public Movie() {
    }

    public Movie(Long id, String title, String description, LocalDate releaseDate, Integer duration, String genre, String posterUrl, Director director, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.genre = genre;
        this.posterUrl = posterUrl;
        this.director = director;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", director=" + director +
                ", category=" + category +
                '}';
    }
}