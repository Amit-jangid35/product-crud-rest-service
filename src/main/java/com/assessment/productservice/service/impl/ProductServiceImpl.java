package com.assessment.productservice.service.impl;

import java.util.List;

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

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        log.debug("Fetching product from DB with id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        log.info("Creating new product: {}", request.getName());

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setName(request.getName());
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        product.setPrice(request.getPrice());

        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        log.info("Deleted product with id: {}", id);
    }
}