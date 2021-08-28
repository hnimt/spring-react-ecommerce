package com.minh.Backend.repository;

import com.minh.Backend.entity.Cart;
import com.minh.Backend.entity.CartItem;
import com.minh.Backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    CartItem findByProduct(Product product);

    List<CartItem> findByCart(Cart cart);
}
