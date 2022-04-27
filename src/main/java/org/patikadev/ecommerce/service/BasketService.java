package org.patikadev.ecommerce.service;

import org.patikadev.ecommerce.model.Basket;
import org.patikadev.ecommerce.model.request.CreateBasketRequest;
import org.patikadev.ecommerce.model.response.CreateBasketResponse;

public interface BasketService {
    CreateBasketResponse create(CreateBasketRequest request);


}
