package com.minh.Backend.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ProductCreateDTO {
    private String name;
    private String description;
    private double price;
    private int stock;
    private List<MultipartFile> images;
    private int categoryId;
}
