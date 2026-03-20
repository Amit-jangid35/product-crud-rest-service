package com.assessment.productservice.service;

import java.util.List;

import com.assessment.productservice.DTO.ProductRequest;
import com.assessment.productservice.DTO.ProductResponse;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
