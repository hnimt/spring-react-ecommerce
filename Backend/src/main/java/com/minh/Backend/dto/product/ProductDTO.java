package com.minh.Backend.dto.product;

import com.minh.Backend.dto.category.CategoryDTO;
import com.minh.Backend.entity.image.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private int views;
    private List<ProductImage> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CategoryDTO category;
}
