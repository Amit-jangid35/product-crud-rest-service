package com.assessment.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assessment.productservice.DTO.ProductRequest;
import com.assessment.productservice.DTO.ProductResponse;
import com.assessment.productservice.DTO.RestResponse;
import com.assessment.productservice.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product Management", description = "APIs for managing products")
@Slf4j
public class ProductController {

	private final ProductService productService;

	@Operation(summary = "Get all products")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Products retrieved successfully", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{
					  "status": "SUCCESS",
					  "data": [
					    {
					      "id": 1,
					      "name": "iPhone 15",
					      "description": "Latest Apple iPhone",
					      "price": 79999.99
					    }
					  ],
					  "message": "Products retrieved"
					}
					"""))) })
	@GetMapping
	public ResponseEntity<RestResponse<List<ProductResponse>>> getAll() {
		log.info("Fetching all products");

		return ResponseEntity.ok(RestResponse.success(productService.getAllProducts(), "Products retrieved"));
	}

	@Operation(summary = "Get product by ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Product found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{
					  "status": "SUCCESS",
					  "data": {
					    "id": 1,
					    "name": "iPhone 15",
					    "description": "Latest Apple iPhone",
					    "price": 79999.99
					  },
					  "message": "Product found"
					}
					"""))),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{
					  "status": "FAILURE",
					  "data": null,
					  "error": {
					    "code": 404,
					    "message": "Product not found with id: 1"
					  },
					  "message": "Product not found"
					}
					"""))) })
	@GetMapping("/{id}")
	public ResponseEntity<RestResponse<ProductResponse>> getById(@PathVariable Long id) {
		log.info("Fetching product with id: {}", id);

		return ResponseEntity.ok(RestResponse.success(productService.getProductById(id), "Product found"));
	}

	@Operation(summary = "Create new product")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Product created", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{
					  "status": "SUCCESS",
					  "data": {
					    "id": 1,
					    "name": "iPhone 15",
					    "description": "Latest Apple iPhone",
					    "price": 79999.99
					  },
					  "message": "Product created"
					}
					"""))),
			@ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{
					  "status": "FAILURE",
					  "data": null,
					  "error": {
					    "code": 400,
					    "message": "name: must not be blank"
					  },
					  "message": "Validation failed"
					}
					"""))) })
	@PostMapping
	public ResponseEntity<RestResponse<ProductResponse>> create(@Valid @RequestBody ProductRequest request) {

		log.info("Creating product: {}", request.getName());

		return new ResponseEntity<>(RestResponse.success(productService.createProduct(request), "Product created"),
				HttpStatus.CREATED);
	}

	@Operation(summary = "Update product")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Product updated", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{
					  "status": "SUCCESS",
					  "data": {
					    "id": 1,
					    "name": "Updated iPhone",
					    "description": "Updated description",
					    "price": 89999.99
					  },
					  "message": "Product updated"
					}
					"""))),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{
					  "status": "FAILURE",
					  "data": null,
					  "error": {
					    "code": 404,
					    "message": "Product not found with id: 1"
					  },
					  "message": "Product not found"
					}
					"""))) })
	@PutMapping("/{id}")
	public ResponseEntity<RestResponse<ProductResponse>> update(@PathVariable Long id,
			@Valid @RequestBody ProductRequest request) {

		log.info("Updating product id: {}", id);

		return ResponseEntity.ok(RestResponse.success(productService.updateProduct(id, request), "Product updated"));
	}

	@Operation(summary = "Delete product")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Product deleted"),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
					{
					  "status": "FAILURE",
					  "data": null,
					  "error": {
					    "code": 404,
					    "message": "Product not found with id: 1"
					  },
					  "message": "Product not found"
					}
					"""))) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.info("Deleting product id: {}", id);

		productService.deleteProduct(id);

		return ResponseEntity.noContent().build();
	}
}