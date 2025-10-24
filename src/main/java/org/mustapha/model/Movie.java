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

        private int rating;

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

        public Movie(Long id, String title, String description,int rating, LocalDate releaseDate, Integer duration, String genre, String posterUrl, Director director, Category category) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.releaseDate = releaseDate;
            this.duration = duration;
            this.rating = rating;
            this.posterUrl = posterUrl;
            this.director = director;
            this.category = category;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public LocalDate getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getPosterUrl() {
            return posterUrl;
        }

        public void setPosterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
        }

        public Director getDirector() {
            return director;
        }

        public void setDirector(Director director) {
            this.director = director;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
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
                    ", genre='" + rating + '\'' +
                    ", posterUrl='" + posterUrl + '\'' +
                    ", director=" + director +
                    ", category=" + category +
                    '}';
        }
    }