package org.patikadev.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Basket;
import org.patikadev.ecommerce.model.BasketItem;
import org.patikadev.ecommerce.model.Customer;
import org.patikadev.ecommerce.model.Product;
import org.patikadev.ecommerce.model.enums.BasketStatus;
import org.patikadev.ecommerce.model.request.CreateBasketRequest;
import org.patikadev.ecommerce.model.response.CreateBasketResponse;
import org.patikadev.ecommerce.repository.BasketItemRepository;
import org.patikadev.ecommerce.repository.CustomerRepository;
import org.patikadev.ecommerce.repository.ProductRepository;
import org.patikadev.ecommerce.utils.Calculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class BasketConverterImpl implements BasketConverter {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final ProductRepository productRepository;
    private final BasketItemRepository basketItemRepository;

    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.20);
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.18);

    @Override
    public Basket toCreateBasketRequest(CreateBasketRequest request) {
        Basket basket = new Basket();

        Set<BasketItem> basketItems = new HashSet<>();
        for (BasketItem basketItem : request.items()) {
            BasketItem newItem = new BasketItem();

            Product product = productRepository
                    .findByIdAndIsDeleted(basketItem.getProduct().getId(), false)
                    .orElseThrow(() -> new BusinessServiceOperationException.ProductNotFoundException("Product not found"));
            newItem.setProduct(product);

            BigDecimal price = product.getPrice();
            BigDecimal quantity = basketItem.getQuantity();

            newItem.setQuantity(quantity);
            newItem.setPrice(price);
            newItem.setDiscountPrice(Calculator.getDiscountPrice(price, quantity, DISCOUNT_RATE));
            newItem.setTaxPrice(Calculator.getTaxPrice(price, quantity, TAX_RATE));
            newItem.setShippingPrice(basketItem.getShippingPrice());

//            newItem.setBasket(basket);
            basketItems.add(newItem);
        }
        basket.setItems(basketItems);

        BigDecimal price = Calculator.getItemListPrice(basketItems);
        BigDecimal discountPrice = Calculator.getTotalDiscountPrice(basketItems);
        BigDecimal taxPrice = Calculator.getTotalTaxPrice(basketItems);
        BigDecimal totalQuantity = Calculator.getTotalQuantity(basketItems);
        BigDecimal shippingPrice = Calculator.getTotalShippingPrice(basketItems);
        BigDecimal totalPrice = Calculator.getTotalPrice(price, taxPrice, shippingPrice, discountPrice);

        Customer customer = (customerRepository
                .findByIdAndIsDeleted(request.customerId(), false)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found")));

        basket.setCustomer(customer);

        /* itemlist price without discount */
        basket.setPrice(price);
        /* discount price */
        basket.setDiscountPrice(discountPrice);
        basket.setShippingPrice(shippingPrice);
        /* tax price of priceWithoutDiscount */
        basket.setTaxPrice(taxPrice);
        basket.setTotalQuantity(totalQuantity);
        /* total price = priceWithoutDiscount + taxPrice + shippingPrice - discountPrice */
        basket.setTotalPrice(totalPrice);

        return basket;
    }

    @Override
    public CreateBasketResponse toCreateBasketResponse(Basket basket) {
        BasketStatus status = BasketStatus.INIT;
        return new CreateBasketResponse(basket.getId(),
                basket.getItems(),
                basket.getShippingPrice(),
                basket.getTaxPrice(),
                basket.getTotalQuantity(),
                basket.getDiscountPrice(),
                basket.getTotalPrice(),
                status,
                customerConverter.toGetCustomerResponse(basket.getCustomer()));
    }
}
