package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Basket;
import org.patikadev.ecommerce.model.request.CreateBasketRequest;
import org.patikadev.ecommerce.model.response.CreateBasketResponse;

public interface BasketConverter {

    Basket toCreateBasketRequest(CreateBasketRequest request);

    CreateBasketResponse toCreateBasketResponse(Basket basket);

}
