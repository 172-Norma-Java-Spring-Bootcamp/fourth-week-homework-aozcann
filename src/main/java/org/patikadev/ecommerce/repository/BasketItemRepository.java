
package org.patikadev.ecommerce.repository;

import org.patikadev.ecommerce.model.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
}
