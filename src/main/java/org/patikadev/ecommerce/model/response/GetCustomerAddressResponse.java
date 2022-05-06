package org.patikadev.ecommerce.model.response;

public record GetCustomerAddressResponse(String phoneNumber,
                                         String country,
                                         String city,
                                         String postalCode,
                                         String description) {
}