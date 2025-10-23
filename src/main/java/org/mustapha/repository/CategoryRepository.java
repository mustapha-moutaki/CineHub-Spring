package org.mustapha.repository;

import org.mustapha.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Spring Data JPA will automatically provide basic CRUD operations
    // You can add custom query methods here if needed, e.g.:
    // Optional<Category> findByName(String name);
}