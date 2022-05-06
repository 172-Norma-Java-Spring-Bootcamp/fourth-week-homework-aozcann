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

    // It will be used 0.18 as default value
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.18);

    @Override
    public Basket toCreateBasket(CreateBasketRequest request) {
        Basket basket = new Basket();
        // If customer is not exist, or it is deleted, it will be throw an exception
        Customer customer = (customerRepository
                .findByIdAndIsDeleted(request.customerId(), false)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found")));

        basket.setCustomer(customer);

        basket.setItems(toCalculateBasketItemsPrice(request.items()));
        toCalculateBasketPrice(basket);

        Collection<CampaignHistory> campaignHistoryList = campaignHistoryRepository.findAllByCustomerId(customer.getId());
        // If customer want to use a campaign and campaign code is valid, basket price will be re-calculated
        // It checks campaign code and if it is valid it will use methods.
        Campaign campaign = campaignRepository.findByCodeAndIsDeleted(request.campaignCode(), false).orElse(null);
        // If campaign not found, it returns default basket price or customer will used NewlyRegisteredCampaignDiscount
        if (Objects.nonNull(campaign)) {
            // This control checks the first use of the campaign.
            // If campaignHistoryList is empty this customer can be use campaign NewlyRegisteredCampaignDiscount
            if (campaignHistoryList.isEmpty()) {
                NewlyRegisteredCampaignDiscount newlyRegisteredCampaignDiscount = new NewlyRegisteredCampaignDiscount();
                // If customer send over the limit campaign code. It will be throw exception.
                if (Objects.equals(campaign.getUsageLimit(), campaign.getCurrentUsageCount())) {
                    throw new BusinessServiceOperationException.CampaignOverLimitException("Campaign code is over");
                } else {
                    // It will be determined which campaign use according to the campaign type.
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
                // If the customer has used a campaign before, only apply the given campaign.
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
            // If the campaign was used, a new campaign history record will be created and the current usage limit will be increased by one.
            campaign.setCurrentUsageCount(campaign.getCurrentUsageCount().add(new BigDecimal(1)));
            campaignRepository.save(campaign);
            campaignHistoryService.create(customer.getId(), campaign.getId(), basket.getDiscountPrice());
        } else {
            // This control checks the first use of the campaign.
            // If campaignHistoryList is empty this customer will be use campaign NewlyRegisteredCampaignDiscount
            if (campaignHistoryList.isEmpty()) {
                NewlyRegisteredCampaignDiscount newlyRegisteredCampaignDiscount = new NewlyRegisteredCampaignDiscount();
                BigDecimal totalPriceWithDiscount = newlyRegisteredCampaignDiscount.apply(basket.getPrice());
                setterTotalPriceAndDiscountPrice(basket, totalPriceWithDiscount);
            }
            // If the campaign was used, a new campaign history record will be created and the current usage limit will be increased by one.
            campaign.setCurrentUsageCount(campaign.getCurrentUsageCount().add(new BigDecimal(1)));
            campaignRepository.save(campaign);
            campaignHistoryService.create(customer.getId(), campaign.getId(), basket.getDiscountPrice());
        }
        return basket;
    }

    // It set total price and discount price from basket
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

    /*
     * It calculates all basket items price with tax and shipping prices,
     * It takes all basket items and take one by one basket item
     * It sets price,quantity,tax price and shipping price and returns basket items
     */
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

    /*
     * It is calculated all basket items price
     */
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
