package org.patikadev.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.model.request.CreateOrUpdateCustomerRequest;
import org.patikadev.ecommerce.model.response.CreateCustomerResponse;
import org.patikadev.ecommerce.model.response.GetCustomerResponse;
import org.patikadev.ecommerce.service.CustomerService;
import org.patikadev.ecommerce.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final Validator<CreateOrUpdateCustomerRequest> createCustomerRequestValidator;
    private final Validator<Long> idValidator;
    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<CreateCustomerResponse> create(@RequestBody CreateOrUpdateCustomerRequest request) {
        createCustomerRequestValidator.validate(request);
        return ResponseEntity.ok(customerService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable Long id) {
        idValidator.validate(id);
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCustomerResponse> update(@RequestBody CreateOrUpdateCustomerRequest request,
                                                      @PathVariable Long id) {
        idValidator.validate(id);
        return ResponseEntity.ok(customerService.updateCustomer(request,id));
    }

    @GetMapping
    public ResponseEntity<Collection<GetCustomerResponse>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id,
                                                @RequestParam(name = "isHardDeleted", required = false) boolean isHardDeleted) {
        idValidator.validate(id);
        return ResponseEntity.ok(customerService.deleteCustomerById(id, isHardDeleted));
    }
}
