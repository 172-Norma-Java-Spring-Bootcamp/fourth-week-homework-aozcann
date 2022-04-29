package org.patikadev.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Basket;
import org.patikadev.ecommerce.model.BasketItem;
import org.patikadev.ecommerce.model.Customer;
import org.patikadev.ecommerce.model.Product;
import org.patikadev.ecommerce.model.request.CreateBasketRequest;
import org.patikadev.ecommerce.model.response.CreateBasketResponse;
import org.patikadev.ecommerce.model.response.GetBasketItemResponse;
import org.patikadev.ecommerce.model.response.GetProductResponse;
import org.patikadev.ecommerce.repository.CustomerRepository;
import org.patikadev.ecommerce.repository.ProductRepository;
import org.patikadev.ecommerce.utils.Calculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class BasketConverterImpl implements BasketConverter {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.20);
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.18);

    @Override
    public Basket toCreateBasket(CreateBasketRequest request) {
        Basket basket = new Basket();
        basket.setItems(toCalculateBasketItemsPrice(request.items()));

        toCalculateBasketPrice(basket);

        Customer customer = (customerRepository
                .findByIdAndIsDeleted(request.customerId(), false)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found")));

        basket.setCustomer(customer);

        return basket;
    }

    @Override
    public CreateBasketResponse toCreateBasketResponse(Basket basket) {
        return new CreateBasketResponse(basket.getId(),
                toConvertBasketItem(basket.getItems()),
                basket.getShippingPrice(),
                basket.getTaxPrice(),
                basket.getTotalQuantity(),
                basket.getDiscountPrice(),
                basket.getTotalPrice(),
                customerConverter.toGetCustomerResponse(basket.getCustomer()));
    }

    @Override
    public void toReCalculateBasketPrice(Basket basket) {
        toCalculateBasketItemsPrice(basket.getItems());
        toCalculateBasketPrice(basket);

    }

    public Set<BasketItem> toCalculateBasketItemsPrice(Set<BasketItem> basketItems) {
        Set<BasketItem> items = new HashSet<>();

        for (BasketItem basketItem : basketItems) {

            Product product = productRepository
                    .findByIdAndIsDeleted(basketItem.getProduct().getId(), false)
                    .orElseThrow(() -> new BusinessServiceOperationException.ProductNotFoundException("Product not found"));
            basketItem.setProduct(product);


            BigDecimal price = basketItem.getProduct().getPrice();
            BigDecimal quantity = basketItem.getQuantity();

            basketItem.setQuantity(quantity);
            basketItem.setPrice(price);
            basketItem.setDiscountPrice(Calculator.getDiscountPrice(price, quantity, DISCOUNT_RATE));
            basketItem.setTaxPrice(Calculator.getTaxPrice(price, quantity, TAX_RATE));
            basketItem.setShippingPrice(basketItem.getShippingPrice());

            items.add(basketItem);
        }
        return items;
    }

    public void toCalculateBasketPrice(Basket basket) {
        Set<BasketItem> basketItems = basket.getItems();

        BigDecimal price = Calculator.getItemListPrice(basketItems);
        BigDecimal discountPrice = Calculator.getTotalDiscountPrice(basketItems);
        BigDecimal taxPrice = Calculator.getTotalTaxPrice(basketItems);
        BigDecimal totalQuantity = Calculator.getTotalQuantity(basketItems);
        BigDecimal shippingPrice = Calculator.getTotalShippingPrice(basketItems);
        BigDecimal totalPrice = Calculator.getTotalPrice(price, taxPrice, shippingPrice, discountPrice);

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
        basket.setCreatedAt(new Date());
        basket.setCreatedBy("AhmetOzcan");
    }

    public Set<GetBasketItemResponse> toConvertBasketItem(Set<BasketItem> basketItems) {
        Set<GetBasketItemResponse> items = new HashSet<>();
        for (BasketItem item : basketItems) {
            GetBasketItemResponse getBasketItemResponse = new GetBasketItemResponse(
                    item.getId(),
                    productConverter.toGetBasketItemProductResponse(item.getProduct()),
                    item.getQuantity(),
                    item.getPrice(),
                    item.getDiscountPrice(),
                    item.getTaxPrice(),
                    item.getShippingPrice()
            );
            items.add(getBasketItemResponse);
        }
        return items;
    }
}
