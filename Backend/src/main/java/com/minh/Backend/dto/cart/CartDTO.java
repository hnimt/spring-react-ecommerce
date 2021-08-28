package com.minh.Backend.dto.cart;

import com.minh.Backend.dto.cartitem.CartItemDTO;
import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private int id;
    private double total;
    private double discount;
    private double tax;
    private List<CartItemDTO> cartItems;
}
