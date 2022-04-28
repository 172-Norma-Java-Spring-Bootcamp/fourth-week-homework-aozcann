package org.patikadev.ecommerce.model.request;

import org.patikadev.ecommerce.model.enums.PaymentType;

public record PaymentRequest(Long basketId,
                             PaymentType paymentType,
                             String cardNumber,
                             String cardOwnerName,
                             String cardLastDate,
                             String ccv) {
}
