package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.converter.CustomerConverter;
import org.patikadev.ecommerce.exception.BaseException;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Customer;
import org.patikadev.ecommerce.model.request.CreateCustomerRequest;
import org.patikadev.ecommerce.model.response.CreateCustomerResponse;
import org.patikadev.ecommerce.model.response.GetCustomerResponse;
import org.patikadev.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerConverter customerConverter;
    private final CustomerRepository customerRepository;

    @Override
    public CreateCustomerResponse create(CreateCustomerRequest request) {
        Customer customer = customerConverter.toCreateCustomerRequest(request);
        customerRepository.save(customer);
        log.info("Customer created successfully by id -> {}", customer.getId());
        return new CreateCustomerResponse(customer.getId());
    }

    @Override
    public GetCustomerResponse getCustomer(Long id) {
        Customer customer = customerRepository
                .findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found"));
        log.info("Customer returned successfully by id -> {}", customer.getId());
        return customerConverter.toGetCustomerResponse(customer);
    }

    @Override
    public Collection<GetCustomerResponse> getAllCustomer() {
        List<GetCustomerResponse> customerResponseList = customerRepository.findAllByIsDeleted(false)
                .stream()
                .map(customerConverter::toGetCustomerResponse)
                .toList();
        log.info("Customer list returned successfully.");
        return customerResponseList;

    }

    @Override
    public boolean deleteCustomerById(Long id, boolean isHardDeleted) throws BaseException {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found."));
        if (isHardDeleted) {
            customerRepository.delete(customer);
            return true;
        }
        if (customer.isDeleted()) {
            log.error("Customer already deleted by id: {}", id);
            throw new BusinessServiceOperationException.CustemerAlreadyDeletedException("Customer already deleted.");
        }
        customer.setDeleted(true);
        customer.getCustomerAddress().setDeleted(true);
        customerRepository.save(customer);
        log.info("Customer deleted successfully.");

        return customer.isDeleted();
    }

}
