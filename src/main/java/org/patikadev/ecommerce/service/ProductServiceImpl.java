package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.converter.ProductConverter;
import org.patikadev.ecommerce.exception.BaseException;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Product;
import org.patikadev.ecommerce.model.request.CreateProductRequest;
import org.patikadev.ecommerce.model.response.CreateProductResponse;
import org.patikadev.ecommerce.model.response.GetProductResponse;
import org.patikadev.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Override
    public CreateProductResponse create(CreateProductRequest request) {
        Product product = productConverter.toCreateProductRequest(request);
        productRepository.save(product);
        log.info("Product created successfully by id -> {}", product.getId());
        return productConverter.toCreateProductResponse(product);
    }

    @Override
    public GetProductResponse getProduct(Long id) {
        Product product = productRepository
                .findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new BusinessServiceOperationException.ProductNotFoundException("Product not found."));
        log.info("Product returned successfully by id -> {}", product.getId());
        return productConverter.toGetProductResponse(product);
    }

    @Override
    public Collection<GetProductResponse> getAllProduct() {
        Collection<GetProductResponse> productResponseList = productRepository
                .findAllByIsDeleted(false)
                .stream()
                .map(productConverter::toGetProductResponse)
                .toList();
        log.info("All product list returned successfully");
        return productResponseList;
    }

    @Override
    public boolean deleteProductById(Long id, boolean isHardDeleted) throws BaseException {
    Product product = productRepository.findById(id).orElseThrow(() -> new BusinessServiceOperationException.ProductNotFoundException("Product not found") );
    if (isHardDeleted){
        productRepository.delete(product);
        log.info("Product hard deleted successfully.");
        return true;
    }
    if (product.isDeleted()){
        log.error("Product already deleted by id -> {}",id);
        throw new BusinessServiceOperationException.
                ProductAlreadyDeletedException("Product already deleted.");
    }
    product.setDeleted(true);
        log.info("Product deleted successfully by id -> {}", id);
        productRepository.save(product);
        return true;
    }

}
