package org.patikadev.ecommerce.repository;

import org.patikadev.ecommerce.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
