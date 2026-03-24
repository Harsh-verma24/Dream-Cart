# Product Model and Service Documentation

## Product Model

Represents a product in the DreamCart system.

### Fields
- **id**: Unique identifier (Long)
- **name**: Product name (String)
- **brand**: Brand name (String)
- **price**: Price (BigDecimal)
- **inventory**: Stock count (int)
- **description**: Product description (String)
- **category**: Linked Category entity
- **images**: List of associated Image entities

## ProductService

Handles business logic for products.

### Main Methods
- **addProduct(AddProductRequest)**: Adds a new product, creating category if needed.
- **getProductById(Long)**: Retrieves a product by ID.
- **deleteProduct(Long)**: Deletes a product by ID.
- **updateProduct(UpdateProductRequest, Long)**: Updates product details.
- **getAllProducts()**: Retrieves all products.
- **getProductsByCategory(String)**: Gets products by category name.
- **getProductsByBrand(String)**: Gets products by brand name.
- **getProductsByCategoryAndBrand(String, String)**: Gets products by category and brand.
- **getProductsByName(String)**: Gets products by name.
- **getProductsByBrandAndName(String, String)**: Gets products by brand and name.
- **countProductsByBrandAndName(String, String)**: Counts products by brand and name.
