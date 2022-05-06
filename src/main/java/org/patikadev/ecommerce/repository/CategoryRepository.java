package org.patikadev.ecommerce.repository;

import org.patikadev.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByIdAndIsDeleted(Long id, boolean isDeleted);

    Collection<Category> findByIsDeleted(boolean isDeleted);
}
