package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Customer;
import org.patikadev.ecommerce.model.CustomerAddress;
import org.patikadev.ecommerce.model.request.CreateOrUpdateCustomerRequest;
import org.patikadev.ecommerce.model.response.GetCustomerAddressResponse;
import org.patikadev.ecommerce.model.response.GetCustomerResponse;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class CustomerConverterImpl implements CustomerConverter {

    @Override
    public Customer toCreateCustomer(CreateOrUpdateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setUsername(request.userName());
        customer.setEmail(request.email());
        customer.setIdentity(request.identity());
        customer.setGender(request.gender());
        customer.setPassword(request.password());
        customer.setCreatedAt(new Date());
        customer.setCreatedBy("AhmetOzcan");

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setPhoneNumber(request.customerAddress().phoneNumber());
        customerAddress.setCountry(request.customerAddress().country());
        customerAddress.setCity(request.customerAddress().city());
        customerAddress.setPostalCode(request.customerAddress().postalCode());
        customerAddress.setDescription(request.customerAddress().description());

        customer.setCustomerAddress(customerAddress);

        return customer;
    }

    // It updates just sending fields in request
    @Override
    public Customer toUpdateCustomer(CreateOrUpdateCustomerRequest request, Customer customer) {
        if (Objects.nonNull(request.userName())) {
            customer.setUsername(request.userName());
        }
        if (Objects.nonNull(request.email())) {
            customer.setUsername(request.email());
        }
        if (Objects.nonNull(request.identity())) {
            customer.setIdentity(request.identity());
        }
        if (Objects.nonNull(request.gender())) {
            customer.setGender(request.gender());
        }
        if (Objects.nonNull(request.password())) {
            customer.setPassword(request.password());
        }
        if (Objects.nonNull(request.customerAddress())) {
            CustomerAddress customerAddress = customer.getCustomerAddress();
            if (Objects.nonNull(request.customerAddress().phoneNumber())) {
                customerAddress.setPhoneNumber(request.customerAddress().phoneNumber());
            }
            if (Objects.nonNull(request.customerAddress().country())) {
                customerAddress.setCountry(request.customerAddress().country());
            }
            if (Objects.nonNull(request.customerAddress().city())) {
                customerAddress.setCity(request.customerAddress().city());
            }
            if (Objects.nonNull(request.customerAddress().postalCode())) {
                customerAddress.setPostalCode(request.customerAddress().postalCode());
            }
            if (Objects.nonNull(request.customerAddress().description())) {
                customerAddress.setDescription(request.customerAddress().description());
            }
            customerAddress.setUpdatedAt(new Date());
            customerAddress.setUpdatedBy("AhmetOzcan");
            customer.setCustomerAddress(customerAddress);
        }
        customer.setUpdatedAt(new Date());
        customer.setUpdatedBy("AhmetOzcan");

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
