package org.mustapha.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(nullable = false, unique = true)
    private String name; // nom de la catégorie (Action, Drame, Comédie, Horreur, Science-Fiction, Romance...)

    @Column(length = 500)
    private String description; // description de la catégorie

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Movie> films = new HashSet<>(); // liste des films de cette catégorie

    // Constructors
    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
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

    public Set<Movie> getFilms() {
        return films;
    }

    public void setFilms(Set<Movie> films) {
        this.films = films;
    }

//    // Add a film to the category
//    public void addFilm(Movie film) {
//        this.films.add(film);
//        film.setCategory(this);
//    }
//
//    // Remove a film from the category
//    public void removeFilm(Movie film) {
//        this.films.remove(film);
//        film.setCategory(null);
//    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}