package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Customer;
import org.patikadev.ecommerce.model.CustomerAddress;
import org.patikadev.ecommerce.model.request.CreateCustomerRequest;
import org.patikadev.ecommerce.model.response.GetCustomerAddressResponse;
import org.patikadev.ecommerce.model.response.GetCustomerResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerConverterImpl implements CustomerConverter {


    @Override
    public Customer toCreateCustomerRequest(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setUsername(request.userName());
        customer.setEmail(request.email());
        customer.setIdentity(request.identity());
        customer.setGender(request.gender());
        customer.setPassword(request.password());
        customer.setCreatedAt(new Date());
        customer.setCreatedBy("AhmetOzcan");
        customer.setDeleted(false);

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setPhoneNumber(request.customerAddress().phoneNumber());
        customerAddress.setCountry(request.customerAddress().country());
        customerAddress.setCity(request.customerAddress().city());
        customerAddress.setPostalCode(request.customerAddress().postalCode());
        customerAddress.setDescription(request.customerAddress().description());

        customerAddress.setCustomer(customer);
        customer.setCustomerAddress(customerAddress);

        return customer;
    }

    @Override
    public GetCustomerResponse toGetCustomerResponse(Customer customer) {
        return new GetCustomerResponse(customer.getId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getGender(),
                toGetCustomerAddressResponse(customer.getCustomerAddress()));
    }


    private GetCustomerAddressResponse toGetCustomerAddressResponse(CustomerAddress customerAddress) {
        return new GetCustomerAddressResponse(customerAddress.getPhoneNumber(),
                customerAddress.getCountry(),
                customerAddress.getCity(),
                customerAddress.getPostalCode(),
                customerAddress.getDescription());
    }

}
