package com.spideyharsh.dreamcart.requests;

import com.spideyharsh.dreamcart.model.Category;
import com.spideyharsh.dreamcart.model.Image;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class UpdateProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<Image> images;
}
