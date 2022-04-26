package org.patikadev.ecommerce.model.request;

public record CreateCustomerAddressRequest(String phoneNumber,
                                           String country,
                                           String city,
                                           String postalCode,
                                           String description) {
}
