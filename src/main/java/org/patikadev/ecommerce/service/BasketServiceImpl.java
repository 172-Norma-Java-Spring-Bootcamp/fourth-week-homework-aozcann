package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.converter.BasketConverter;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Basket;
import org.patikadev.ecommerce.model.BasketItem;
import org.patikadev.ecommerce.model.enums.AddOrDeleteStatus;
import org.patikadev.ecommerce.model.request.AddOrDeleteBasketRequest;
import org.patikadev.ecommerce.model.request.CreateBasketRequest;
import org.patikadev.ecommerce.model.response.CreateBasketResponse;
import org.patikadev.ecommerce.repository.BasketItemRepository;
import org.patikadev.ecommerce.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final BasketConverter basketConverter;
    private final BasketItemRepository basketItemRepository;

    @Override
    public CreateBasketResponse create(CreateBasketRequest request) {
        Basket basket = basketConverter.toCreateBasket(request);
        basketRepository.save(basket);
        log.info("Basket created successfully by id -> {}", basket.getId());
        return basketConverter.toCreateBasketResponse(basket);
    }

    @Override
    public CreateBasketResponse addOrDeleteBasketItem(AddOrDeleteBasketRequest request, Long id) {
        Basket basket = basketRepository.findById(id).orElseThrow(() -> new BusinessServiceOperationException.BasketNotFoundException("Basket not found"));
        Set<BasketItem> currentItems = basket.getItems();
        Set<BasketItem> newItems = request.items();
        // It's check request items list  and basket items has a same product,
        // if they have a same product new ıtem list will add or subtract to basket items depend on AddOrDeleteStatus.
        for (BasketItem newItem : newItems) {
            BasketItem currentItem = currentItems.stream().filter(c -> c.getProduct().getId().equals(newItem.getProduct().getId())).findFirst().orElse(null);
            // If one of the current items' product contains new items product
            if (Objects.nonNull(currentItem)) {
                if (request.status().equals(AddOrDeleteStatus.ADD)) {
                    // If AddOrDeleteStatus is ADD, update currentItem quantity.
                    currentItem.setQuantity(currentItem.getQuantity().add(newItem.getQuantity()));
                } else {
                    int quantity = currentItem.getQuantity().subtract(newItem.getQuantity()).compareTo(BigDecimal.ZERO);
                    if (quantity < 0) {
                        // Basket item quantity can not be less than zero
                        throw new BusinessServiceOperationException.BasketItemQuantityCanNotBeLessThanZero("Basket item quantity can not be less than zero");
                    } else if (quantity == 0) {
                        // If quantity is zero, remove current item from the basket item list
                        currentItems.remove(currentItem);
                        basketItemRepository.delete(currentItem);
                    } else {
                        // If AddOrDeleteStatus is DELETE, update currentItem quantity.
                        currentItem.setQuantity(currentItem.getQuantity().subtract(newItem.getQuantity()));
                    }
                }
            } else if (request.status().equals(AddOrDeleteStatus.ADD)) {
                // If it not contains same product and status is ADD, the newItem will be added on the basket items.
                currentItems.add(newItem);
            } else {
                // If it not contains same product and status is DELETE, throws exceptions.
                // Because non-existent product can not be deleted from the current basket item list
                throw new BusinessServiceOperationException.ProductNotFoundException("Product not found");
            }
        }
        basket.setUpdatedAt(new Date());
        basket.setUpdatedBy("AhmetOzcan");
        basket.setItems(currentItems);
        basketConverter.toReCalculateBasketPrice(basket);
        basketRepository.save(basket);

        return basketConverter.toCreateBasketResponse(basket);
    }
}
