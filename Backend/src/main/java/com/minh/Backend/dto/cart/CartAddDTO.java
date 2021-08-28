package com.minh.Backend.dto.cart;

import lombok.Data;

@Data
public class CartAddDTO {
    private int productId;
    private int cartId;
    private int quantity;
}
