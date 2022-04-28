package org.patikadev.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.model.request.AddOrDeleteBasketRequest;
import org.patikadev.ecommerce.model.request.CreateBasketRequest;
import org.patikadev.ecommerce.model.response.CreateBasketResponse;
import org.patikadev.ecommerce.service.BasketService;
import org.patikadev.ecommerce.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/baskets")
public class BasketController {

    private final Validator<CreateBasketRequest> createBasketRequestValidator;
    private final Validator<Long> idValidator;
    private final BasketService basketService;

    @PostMapping
    public ResponseEntity<CreateBasketResponse> create(@RequestBody CreateBasketRequest request) {
        createBasketRequestValidator.validate(request);
        return ResponseEntity.ok(basketService.create(request));
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<CreateBasketResponse> addOrDeleteBasketItem(@RequestBody AddOrDeleteBasketRequest request,
                                                                      @PathVariable Long id) {
        idValidator.validate(id);
        return ResponseEntity.ok(basketService.addOrDeleteBasketItem(request, id));

    }
}
