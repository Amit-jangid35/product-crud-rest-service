package com.assessment.productservice.mapper;

import com.assessment.productservice.DTO.ProductResponse;
import com.assessment.productservice.entity.Product;

public class ProductMapper {

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
