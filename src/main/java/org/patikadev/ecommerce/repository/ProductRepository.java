package org.patikadev.ecommerce.repository;

import org.patikadev.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndIsDeleted(Long id, boolean isDeleted);

    Collection<Product> findAllByIsDeleted(boolean isDeleted);
}
