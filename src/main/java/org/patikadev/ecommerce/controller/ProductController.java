package org.patikadev.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.model.request.CreateProductRequest;
import org.patikadev.ecommerce.model.response.CreateProductResponse;
import org.patikadev.ecommerce.model.response.GetProductResponse;
import org.patikadev.ecommerce.service.ProductService;
import org.patikadev.ecommerce.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final Validator<CreateProductRequest> createProductValidator;
    private final Validator<Long> idValidator;
    private final Validator<BigDecimal> priceValidator;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<CreateProductResponse> create(@RequestBody CreateProductRequest request) {
        createProductValidator.validate(request);
        priceValidator.validate(request.price());
        idValidator.validate(request.brand().id());
        idValidator.validate(request.category().id());
        return ResponseEntity.ok(productService.create(request));
    }


    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable Long id) {
        idValidator.validate(id);
        return ResponseEntity.ok(productService.getProduct(id));

    }

    @GetMapping
    public ResponseEntity<Collection<GetProductResponse>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id,
                                               @RequestParam(name = "isHardDeleted",required = false) boolean isHardDeleted){
        idValidator.validate(id);
        return ResponseEntity.ok(productService.deleteProductById(id, isHardDeleted));
    }
}

