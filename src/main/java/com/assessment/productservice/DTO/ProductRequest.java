package com.assessment.productservice.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Request object for creating or updating a product")
public class ProductRequest {

    @Schema(
        description = "Name of the product",
        example = "iPhone 15",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Schema(
        description = "Detailed description of the product",
        example = "Latest Apple iPhone with A16 chip"
    )
    private String description;

    @Schema(
        description = "Price of the product",
        example = "799.99",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;
}
