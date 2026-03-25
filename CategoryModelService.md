# Category Model and Service Documentation

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
