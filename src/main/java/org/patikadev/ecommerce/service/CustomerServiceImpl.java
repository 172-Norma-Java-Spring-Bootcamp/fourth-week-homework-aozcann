package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.converter.CustomerConverter;
import org.patikadev.ecommerce.exception.BaseException;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Customer;
import org.patikadev.ecommerce.model.request.CreateOrUpdateCustomerRequest;
import org.patikadev.ecommerce.model.response.CreateCustomerResponse;
import org.patikadev.ecommerce.model.response.GetCustomerResponse;
import org.patikadev.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerConverter customerConverter;
    private final CustomerRepository customerRepository;

    @Override
    public CreateCustomerResponse create(CreateOrUpdateCustomerRequest request) {
        Customer customer = customerConverter.toCreateCustomer(request);
        customerRepository.save(customer);
        log.info("Customer created successfully by id -> {}", customer.getId());
        return new CreateCustomerResponse(customer.getId());
    }

    @Override
    public GetCustomerResponse updateCustomer(CreateOrUpdateCustomerRequest request, Long id) {
        Customer customer = customerRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found"));
        Customer updateCustomer = customerConverter.toUpdateCustomer(request, customer);
        customer.setUpdatedAt(new Date());
        customer.setUpdatedBy("AhmetOzcan");
        customerRepository.save(updateCustomer);
        log.info("Customer updated successfully by id -> {}", customer.getId());
        return customerConverter.toGetCustomerResponse(customer);
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
            log.info("Customer hard deleted successfully.");
            return true;
        }
        if (customer.isDeleted()) {
            log.error("Customer id:{} already deleted.", id);
            throw new BusinessServiceOperationException.CustemerAlreadyDeletedException("Customer already deleted.");
        }
        customer.setDeleted(true);
        customer.getCustomerAddress().setDeleted(true);
        customer.setDeletedAt(new Date());
        customer.setDeletedBy("AhmetOzcan");
        customerRepository.save(customer);
        log.info("Customer soft deleted successfully by id -> {}",customer.getId());

        return customer.isDeleted();
    }
}
