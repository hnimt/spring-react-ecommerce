package com.minh.Backend.service.impl;

import com.minh.Backend.entity.Cart;
import com.minh.Backend.entity.CartItem;
import com.minh.Backend.entity.Product;
import com.minh.Backend.exception.ResourceNotFoundException;
import com.minh.Backend.repository.CartItemRepository;
import com.minh.Backend.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem findById(Integer id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot found cart item: " + id));
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void remove(Integer id) {
        cartItemRepository.deleteById(id);
    }

    public CartItem findByProduct (Product product) {
        return cartItemRepository.findByProduct(product);
    }

    public List<CartItem> findByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }
}
