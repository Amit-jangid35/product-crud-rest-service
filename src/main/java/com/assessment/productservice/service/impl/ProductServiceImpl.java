package com.assessment.productservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.assessment.productservice.DTO.ProductRequest;
import com.assessment.productservice.DTO.ProductResponse;
import com.assessment.productservice.entity.Product;
import com.assessment.productservice.exception.ResourceNotFoundException;
import com.assessment.productservice.mapper.ProductMapper;
import com.assessment.productservice.repository.ProductRepository;
import com.assessment.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public List<ProductResponse> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        log.debug("Fetching product from DB with id: {}", id);

        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        log.info("Creating new product: {}", request.getName());

        Product saved = repository.save(product);
        return ProductMapper.toResponse(saved);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        Product updated = repository.save(product);
        return ProductMapper.toResponse(updated);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        repository.delete(product);
        log.info("Deleted product with id: {}", id);
    }
}