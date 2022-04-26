package org.patikadev.ecommerce.model.request;

import org.patikadev.ecommerce.model.Gender;

public record CreateCustomerRequest(String userName,
                                    String email,
                                    Long identity,
                                    Gender gender,
                                    String password,
                                    CreateCustomerAddressRequest customerAddress) {

}
