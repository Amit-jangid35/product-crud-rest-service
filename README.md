# product-crud-rest-service

A Production-ready RESTful CRUD API built with **Spring Boot 3.5.x** that performs full CRUD operations on a `Product` resource. The project demonstrates industry-standard practices including layered architecture (Controller → Service → Repository), Bean Validation (JSR 380) with custom error messages, centralized exception handling via `@ControllerAdvice`, JPA auditing, AOP-based performance logging, and interactive API documentation through OpenAPI 3 (Swagger UI).

---

## Tech Stack

- Java 21
- Spring Boot 3.5.x
- Spring Data JPA
- MySQL
- Lombok
- SpringDoc OpenAPI (Swagger UI)

---

## Prerequisites

- Java 21+
- Maven 3.8+
- MySQL 8.0+
- Lombok plugin installed in your IDE (**mandatory** — without it your IDE will show false compilation errors)

### MySQL Setup

1. Install and start MySQL on your machine.
2. The application will automatically create the `productservice` database on first run (via `createDatabaseIfNotExist=true`).
3. Open `src/main/resources/application.properties` and update your MySQL credentials:

```properties
spring.datasource.username=<your_mysql_username>
spring.datasource.password=<your_mysql_password>
```

---

## Building and Running

**Clone the repository:**
```bash
git clone https://github.com/Amit-jangid35/product-crud-rest-service.git
cd product-crud-rest-service
```

**Build:**
```bash
./mvnw clean install
```

**Run:**
```bash
./mvnw spring-boot:run
```

The application starts on `http://localhost:8080`.

---

## API Endpoints

Base path: `/api/v1/products`

| Method | Endpoint                | Description          |
|--------|-------------------------|----------------------|
| GET    | `/api/v1/products`      | Get all products     |
| GET    | `/api/v1/products/{id}` | Get product by ID    |
| POST   | `/api/v1/products`      | Create a product     |
| PUT    | `/api/v1/products/{id}` | Update a product     |
| DELETE | `/api/v1/products/{id}` | Delete a product     |

### Sample Request Body (POST / PUT)

```json
{
  "name": "iPhone 15",
  "description": "Latest Apple iPhone",
  "price": 79999.99
}
```

### Sample Response

```json
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
```

---

## Validation Rules

| Field   | Constraint                       |
|---------|----------------------------------|
| `name`  | Not blank, max 100 characters    |
| `price` | Not null, must be greater than 0 |

Validation errors return a `400 Bad Request` with a descriptive message.

---

## Error Handling

All errors are handled globally via `@RestControllerAdvice` and return a consistent response:

```json
{
  "status": "FAILURE",
  "data": null,
  "error": {
    "code": 404,
    "message": "Product not found with id: 1"
  },
  "message": "Product not found"
}
```

---

## Swagger UI

This project uses **SpringDoc OpenAPI 3** to auto-generate interactive API documentation from the code — no manual YAML/JSON spec needed.

### What Swagger UI Provides

- A browser-based UI to **visualize all API endpoints** with their HTTP methods, paths, and descriptions.
- The ability to **execute live API requests** directly from the browser without needing Postman or curl.
- Auto-generated **request/response schemas** based on your DTOs and annotations.
- Displays **validation constraints** and **example values** defined in the code.
- Shows all possible **response codes** (200, 201, 400, 404, etc.) with example response bodies.

### How to Access

Once the application is running:

| Resource         | URL                                          |
|------------------|----------------------------------------------|
| Swagger UI       | http://localhost:8080/swagger-ui/index.html  |
| OpenAPI JSON     | http://localhost:8080/v3/api-docs            |

### How to Use Swagger UI

1. Open `http://localhost:8080/swagger-ui/index.html` in your browser.
2. You will see the **Product Management** section listing all 5 endpoints.
3. Click on any endpoint to expand it.
4. Click **Try it out** to enable the input fields.
5. Fill in the request body or path parameters as needed.
6. Click **Execute** to send the request and see the live response.

### Annotations Used in This Project

| Annotation       | Purpose                                              |
|------------------|------------------------------------------------------|
| `@Tag`           | Groups endpoints under a named section in the UI     |
| `@Operation`     | Adds a summary/description to an individual endpoint |
| `@ApiResponse`   | Documents a specific HTTP response code              |
| `@ExampleObject` | Provides a sample JSON response body in the UI       |
