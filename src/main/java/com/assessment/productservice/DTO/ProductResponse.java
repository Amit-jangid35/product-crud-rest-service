package com.assessment.productservice.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Product response payload")
public class ProductResponse {

	@Schema(description = "Product ID", example = "1")
	private Long id;

	@Schema(description = "Name of the product", example = "iPhone 15")
	private String name;

	@Schema(description = "Product description", example = "Latest Apple iPhone")
	private String description;

	@Schema(description = "Price of the product", example = "79999.99")
	private Double price;
}
