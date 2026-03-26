package com.spideyharsh.dreamcart.controller;


import com.spideyharsh.dreamcart.exceptions.AlreadyExistsException;
import com.spideyharsh.dreamcart.exceptions.ResourceNotFoundException;
import com.spideyharsh.dreamcart.model.Category;
import com.spideyharsh.dreamcart.response.ApiResponse;
import com.spideyharsh.dreamcart.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Found !", categories));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error",INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try {
            Category theCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Added", theCategory));
        }
        catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/category/{categoryId}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId) {
        try {
            Category theCategory = categoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Found", theCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @GetMapping("/category/{categoryName}/category")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String categoryName) {
        try {
            Category theCategory = categoryService.getCategoryByName(categoryName);
            return ResponseEntity.ok(new ApiResponse("Found", theCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @DeleteMapping("/category/{categoryId}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PutMapping("/category/{categoryId}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(category, categoryId);
            return ResponseEntity.ok(new ApiResponse("Updated successfully", updatedCategory));
        } catch (ResourceNotFoundException e) {
           return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
