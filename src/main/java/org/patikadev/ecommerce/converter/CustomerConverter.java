package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Customer;
import org.patikadev.ecommerce.model.request.CreateOrUpdateCustomerRequest;
import org.patikadev.ecommerce.model.response.GetCustomerResponse;

public interface CustomerConverter {

    Customer toCreateCustomer(CreateOrUpdateCustomerRequest request);

    GetCustomerResponse toGetCustomerResponse(Customer customer);

    Customer toUpdateCustomer(CreateOrUpdateCustomerRequest request, Customer customer);
}
