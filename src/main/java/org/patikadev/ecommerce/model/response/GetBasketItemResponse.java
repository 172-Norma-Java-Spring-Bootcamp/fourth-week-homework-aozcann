package org.patikadev.ecommerce.model.response;

import java.math.BigDecimal;

public record GetBasketItemResponse(Long id,
                                    GetBasketItemProductResponse product,
                                    BigDecimal quantity,
                                    BigDecimal price,
                                    BigDecimal discountPrice,
                                    BigDecimal taxPrice,
                                    BigDecimal shippingPrice) {
}
