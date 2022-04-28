package org.patikadev.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.model.enums.OrderStatus;
import org.patikadev.ecommerce.model.request.CreateBasketRequest;
import org.patikadev.ecommerce.model.request.OrderStatusRequest;
import org.patikadev.ecommerce.model.request.PaymentRequest;
import org.patikadev.ecommerce.model.response.CreateBasketResponse;
import org.patikadev.ecommerce.model.response.OrderUpdateResponse;
import org.patikadev.ecommerce.model.response.PaymentResponse;
import org.patikadev.ecommerce.service.BasketService;
import org.patikadev.ecommerce.service.OrderService;
import org.patikadev.ecommerce.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final Validator<PaymentRequest> paymentRequestValidator;
    private final Validator<Long> idValidator;
    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<PaymentResponse> payment(@RequestBody PaymentRequest request){
        paymentRequestValidator.validate(request);
        idValidator.validate(request.basketId());
        return ResponseEntity.ok(orderService.payment(request));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<OrderUpdateResponse> updateOrderStatus(@PathVariable Long id,
                                                                 @RequestParam OrderStatus status){
        idValidator.validate(id);
        return ResponseEntity.ok(orderService.updateStatus(id,status));

    }

}
