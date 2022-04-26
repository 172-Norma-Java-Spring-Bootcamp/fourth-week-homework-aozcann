package org.patikadev.ecommerce.repository;

import org.patikadev.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByIdAndIsDeleted(Long id, boolean isDeleted);

    Collection<Customer> findAllByIsDeleted(boolean isDeleted);

}
