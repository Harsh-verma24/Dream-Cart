package com.spideyharsh.dreamcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Image> images;

    public Product(Category category, String description, BigDecimal price, String name, String brand, int inventory) {
        this.category = category;
        this.description = description;
        this.price = price;
        this.name = name;
        this.brand = brand;
        this.inventory = inventory;
    }

    public Product(String name, String brand, String description, BigDecimal price, int inventory, Category category) {
    }
}
