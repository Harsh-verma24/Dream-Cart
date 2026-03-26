# Category Model, Service, and Controller Documentation

## Category Model

Represents a product category in the DreamCart system.

### Fields
- **id**: Unique identifier (Long)
- **name**: Category name (String)
- **products**: List of Product entities associated with this category

### Annotations
- `@Entity`: Marks this as a JPA entity
- Lombok: `@Getter`, `@Setter`, `@AllArgsConstructor`, `@NoArgsConstructor` for boilerplate code
- `@OneToMany(mappedBy = "category", ...)` links products to this category

---

## CategoryService & ICategoryService

Handles business logic for categories.

### Main Methods
- **getCategoryById(Long)**: Retrieves a category by ID, throws if not found
- **getCategoryByName(String)**: Retrieves a category by name
- **getAllCategories()**: Lists all categories
- **addCategory(Category)**: Adds a new category, throws if name exists
- **updateCategory(Category, Long)**: Updates category name by ID
- **deleteCategoryById(Long)**: Deletes a category by ID

### Exceptions
- Throws `ResourceNotFoundException` if category not found
- Throws `AlreadyExistsException` if category name exists

---

## Category Controller

The Category controller exposes RESTful endpoints for category operations. Example endpoints:
- `POST /categories` - Create a new category
- `GET /categories/{id}` - Retrieve category by ID
- `GET /categories` - List all categories
- `PUT /categories/{id}` - Update category by ID
- `DELETE /categories/{id}` - Delete category by ID

### Controller Responsibilities
- Validate incoming requests
- Call appropriate service methods
- Handle responses and errors
- Enforce authentication/authorization as needed
