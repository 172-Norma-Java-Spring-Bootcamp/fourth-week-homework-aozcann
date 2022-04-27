package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.converter.BasketConverter;
import org.patikadev.ecommerce.model.Basket;
import org.patikadev.ecommerce.model.request.CreateBasketRequest;
import org.patikadev.ecommerce.model.response.CreateBasketResponse;
import org.patikadev.ecommerce.repository.BasketRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final BasketConverter basketConverter;

    @Override
    public CreateBasketResponse create(CreateBasketRequest request) {
        Basket basket = basketConverter.toCreateBasketRequest(request);
        basketRepository.save(basket);
        log.info("Basket created successfully by id -> {}", basket.getId());
        return basketConverter.toCreateBasketResponse(basket);
    }
}
