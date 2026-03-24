package com.spideyharsh.dreamcart.repository;

import com.spideyharsh.dreamcart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
