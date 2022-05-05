package org.patikadev.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.*;
import org.patikadev.ecommerce.model.enums.CampaignType;
import org.patikadev.ecommerce.model.request.CreateBasketRequest;
import org.patikadev.ecommerce.model.response.CreateBasketResponse;
import org.patikadev.ecommerce.model.response.GetBasketItemResponse;
import org.patikadev.ecommerce.repository.CampaignHistoryRepository;
import org.patikadev.ecommerce.repository.CampaignRepository;
import org.patikadev.ecommerce.repository.CustomerRepository;
import org.patikadev.ecommerce.repository.ProductRepository;
import org.patikadev.ecommerce.service.CampaignHistoryService;
import org.patikadev.ecommerce.service.CampaignHistoryServiceImpl;
import org.patikadev.ecommerce.utils.Calculator;
import org.patikadev.ecommerce.utils.discount.AmountDiscount;
import org.patikadev.ecommerce.utils.discount.NewlyRegisteredCampaignDiscount;
import org.patikadev.ecommerce.utils.discount.RateDiscount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@Component
public class BasketConverterImpl implements BasketConverter {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CampaignRepository campaignRepository;
    private final CampaignHistoryService campaignHistoryService;
    private final CampaignHistoryRepository campaignHistoryRepository;

    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.18);

    @Override
    public Basket toCreateBasket(CreateBasketRequest request) {
        Basket basket = new Basket();


        Customer customer = (customerRepository
                .findByIdAndIsDeleted(request.customerId(), false)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found")));

        basket.setCustomer(customer);

        basket.setItems(toCalculateBasketItemsPrice(request.items()));
        toCalculateBasketPrice(basket);

        Campaign campaign = campaignRepository.findByCodeAndIsDeleted(request.campaignCode(), false).orElse(null);
        if (Objects.nonNull(campaign)) {

            Collection<CampaignHistory> campaignHistoryList = campaignHistoryRepository.findAllByCustomerId(customer.getId());

            if (campaignHistoryList.isEmpty()) {
                NewlyRegisteredCampaignDiscount newlyRegisteredCampaignDiscount = new NewlyRegisteredCampaignDiscount();
                if (Objects.equals(campaign.getUsageLimit(), campaign.getCurrentUsageCount())) {
                    throw new BusinessServiceOperationException.CampaignNotFoundException("Campaign not found");
                } else {
                    if (campaign.getType() == CampaignType.AMOUNT) {
                        AmountDiscount amountDiscount = new AmountDiscount(newlyRegisteredCampaignDiscount);
                        BigDecimal totalPriceWithDiscount = amountDiscount.apply(basket.getPrice(), campaign.getDiscount());

                        setterTotalPriceAndDiscountPrice(basket, totalPriceWithDiscount);

                    } else {
                        RateDiscount rateDiscount = new RateDiscount(newlyRegisteredCampaignDiscount);
                        BigDecimal totalPriceWithDiscount = rateDiscount.apply(basket.getPrice(), campaign.getDiscount());

                        setterTotalPriceAndDiscountPrice(basket, totalPriceWithDiscount);
                    }
                }
            } else {
                if (Objects.equals(campaign.getUsageLimit(), campaign.getCurrentUsageCount())) {
                    throw new BusinessServiceOperationException.CampaignNotFoundException("Campaign not found");
                } else {
                    if (campaign.getType() == CampaignType.AMOUNT) {
                        AmountDiscount amountDiscount = new AmountDiscount(price -> price);
                        BigDecimal totalPriceWithDiscount = amountDiscount.apply(basket.getPrice(), campaign.getDiscount());

                        setterTotalPriceAndDiscountPrice(basket, totalPriceWithDiscount);

                    } else {
                        RateDiscount rateDiscount = new RateDiscount(null);
                        BigDecimal totalPriceWithDiscount = rateDiscount.apply(basket.getPrice(), campaign.getDiscount());

                        setterTotalPriceAndDiscountPrice(basket, totalPriceWithDiscount);
                    }
                }
            }
            campaignHistoryService.create(customer.getId(), campaign.getId(), basket.getDiscountPrice());
        }
        return basket;
    }

    private void setterTotalPriceAndDiscountPrice(Basket basket, BigDecimal totalPriceWithDiscount) {
        BigDecimal totalPrice = Calculator.getTotalPrice(totalPriceWithDiscount, basket.getTaxPrice(), basket.getShippingPrice());
        basket.setDiscountPrice(basket.getPrice().subtract(totalPriceWithDiscount));
        basket.setTotalPrice(totalPrice);
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
            basketItem.setTaxPrice(Calculator.getTaxPrice(price, quantity, TAX_RATE));
            basketItem.setShippingPrice(basketItem.getShippingPrice());

            items.add(basketItem);
        }
        return items;
    }

    public void toCalculateBasketPrice(Basket basket) {
        Set<BasketItem> basketItems = basket.getItems();

        BigDecimal price = Calculator.getItemListPrice(basketItems);
        BigDecimal discountPrice = BigDecimal.ZERO;
        BigDecimal taxPrice = Calculator.getTotalTaxPrice(basketItems);
        BigDecimal totalQuantity = Calculator.getTotalQuantity(basketItems);
        BigDecimal shippingPrice = Calculator.getTotalShippingPrice(basketItems);
        BigDecimal totalPrice = Calculator.getTotalPrice(price, taxPrice, shippingPrice);

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
                    item.getTaxPrice(),
                    item.getShippingPrice()
            );
            items.add(getBasketItemResponse);
        }
        return items;
    }
}
