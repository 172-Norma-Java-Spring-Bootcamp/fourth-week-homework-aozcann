package org.patikadev.ecommerce.model.request;

import org.patikadev.ecommerce.model.Gender;

public record CreateOrUpdateCustomerRequest(String userName,
                                            String email,
                                            Long identity,
                                            Gender gender,
                                            String password,
                                            CreateCustomerAddressRequest customerAddress) {

}
