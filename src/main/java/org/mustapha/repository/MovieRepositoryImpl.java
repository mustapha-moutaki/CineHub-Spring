//package org.mustapha.repositlry;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import org.mustapha.model.Movie;
//
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository // u say to spring this is a repository so handle repsotoru excetpion adn start transcastions
//@Transactional // handle transactions
//
//public class MovieRepositoryImpl implements MovieRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Movie save(Movie movie) {
//     entityManager.persist(movie);
//     return movie;
//    }
//
//    @Override
//    public void delete(Long id) {
//        Movie movie = entityManager.find(Movie.class, id);
//        if(movie != null) {
//            entityManager.remove(movie);
//        }
//    }
//
//    @Override
//    public void update(Movie movie) {
//        entityManager.merge(movie);
//    }
//
//    @Override
//    public Movie findById(Long id) {
//        return entityManager.find(Movie.class, id);
//    }
//
//    @Override
//    public List<Movie> findAll() {
//        return entityManager.createQuery("SELECT m FROM Movie m", Movie.class)
//                .getResultList();
//    }
//}
