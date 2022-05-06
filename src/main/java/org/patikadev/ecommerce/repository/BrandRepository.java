package org.patikadev.ecommerce.repository;

import org.patikadev.ecommerce.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByIdAndIsDeleted(Long id, boolean isDeleted);

    Collection<Brand> findAllByIsDeleted(boolean isDeleted);
}
