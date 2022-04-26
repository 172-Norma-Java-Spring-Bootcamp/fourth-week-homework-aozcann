package org.patikadev.ecommerce.service;

import org.patikadev.ecommerce.model.request.CreateCustomerRequest;
import org.patikadev.ecommerce.model.response.CreateCustomerResponse;
import org.patikadev.ecommerce.model.response.GetCustomerResponse;

import java.util.Collection;

public interface CustomerService {
    CreateCustomerResponse create(CreateCustomerRequest request);

    GetCustomerResponse getCustomer(Long id);

    Collection<GetCustomerResponse> getAllCustomer();

    boolean deleteCustomerById(Long id, boolean isHardDeleted);

}
