package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.converter.OrderConverter;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Basket;
import org.patikadev.ecommerce.model.Order;
import org.patikadev.ecommerce.model.enums.OrderStatus;
import org.patikadev.ecommerce.model.enums.PaymentStatus;
import org.patikadev.ecommerce.model.enums.PaymentType;
import org.patikadev.ecommerce.model.request.PaymentRequest;
import org.patikadev.ecommerce.model.response.OrderUpdateResponse;
import org.patikadev.ecommerce.model.response.PaymentResponse;
import org.patikadev.ecommerce.repository.BasketRepository;
import org.patikadev.ecommerce.repository.CampaignHistoryRepository;
import org.patikadev.ecommerce.repository.CampaignRepository;
import org.patikadev.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final BasketRepository basketRepository;
    private final OrderConverter orderConverter;
    private final OrderRepository orderRepository;
    private final CampaignRepository campaignRepository;
    private final CampaignHistoryRepository campaignHistoryRepository;

    @Override
    public PaymentResponse payment(PaymentRequest request) {
        Basket basket = basketRepository.findById(request.basketId()).orElseThrow(() -> new BusinessServiceOperationException.BasketNotFoundException("Basket not found."));
        Order order = orderConverter.toCreateOrder(request, basket);
        boolean isPaid = paymentCheck(order.getBasket().getTotalPrice(), request.paymentType(), request.cardNumber(), request.cardOwnerName(), request.cardLastDate(), request.ccv());
        if (isPaid) {
            order.setPaymentStatus(PaymentStatus.PAYMENT_SUCCESS);
            order.setStatus(OrderStatus.ORDER_TAKEN);
        }

        Order savedOrder = orderRepository.save(order);

        return new PaymentResponse(savedOrder.getId(),
                savedOrder.getPaymentStatus(),
                savedOrder.getStatus());
    }

    @Override
    public OrderUpdateResponse updateStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new BusinessServiceOperationException.OrderNotFoundException("Order not found."));
        order.setStatus(status);
        order.setUpdatedAt(new Date());
        order.setUpdatedBy("AhmetOzcan");
        Order savedOrder = orderRepository.save(order);
        return new OrderUpdateResponse(savedOrder.getId(), savedOrder.getStatus());
    }


    private boolean paymentCheck(BigDecimal totalPrice, PaymentType paymentType, String cardNumber, String cardOwnerName, String cardLastDate, String ccv) {

        // It's not real, It must be real paymentCheck from bank or platform.
        // I was check just all object is null or not.
        // I can do this with validation but i need this function.
        if (PaymentType.ON_THE_DOOR.equals(paymentType)) {
            return true;
        }

        return totalPrice.compareTo(BigDecimal.ZERO) > 0
                && !Objects.isNull(cardNumber)
                && !Objects.isNull(cardOwnerName)
                && !Objects.isNull(cardLastDate)
                && !Objects.isNull(ccv);
    }

}
