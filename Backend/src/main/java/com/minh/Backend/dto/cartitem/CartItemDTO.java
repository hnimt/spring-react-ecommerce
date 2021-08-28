package com.minh.Backend.dto.cartitem;

import com.minh.Backend.dto.product.ProductDTO;
import com.minh.Backend.entity.Product;
import lombok.Data;

@Data
public class CartItemDTO {
    private int id;
    private int quantity;
    private double total;
    private ProductDTO product;
}
