package org.patikadev.ecommerce.model.response;

import org.patikadev.ecommerce.model.Gender;

public record GetCustomerResponse(Long id,
                                  String username,
                                  String email,
                                  Gender gender,
                                  GetCustomerAddressResponse getCustomerAddressResponse) {
}