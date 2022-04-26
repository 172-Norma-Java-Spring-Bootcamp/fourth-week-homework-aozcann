package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Customer;
import org.patikadev.ecommerce.model.request.CreateCustomerRequest;
import org.patikadev.ecommerce.model.response.GetCustomerResponse;

public interface CustomerConverter {


    Customer toCreateCustomerRequest(CreateCustomerRequest request);


    GetCustomerResponse toGetCustomerResponse(Customer customer);
}
