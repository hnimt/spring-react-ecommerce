package com.minh.Backend.controller;

import com.minh.Backend.dto.cart.CartAddDTO;
import com.minh.Backend.dto.cart.CartDTO;
import com.minh.Backend.dto.cartitem.CartItemUpdateDTO;
import com.minh.Backend.entity.Cart;
import com.minh.Backend.service.impl.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/carts")
public class CartController {
    @Autowired private CartService cartService;
    @Autowired private ModelMapper map;

    @GetMapping
    public ResponseEntity getAll() {
        List<CartDTO> results = cartService.findAll().stream()
                .map(cart -> map.map(cart, CartDTO.class)).collect(Collectors.toList());
        return new ResponseEntity(results, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCartById(@PathVariable Integer id) {
        CartDTO result = map.map(cartService.findById(id), CartDTO.class);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity addToCart(@RequestBody CartAddDTO cartAddDTO) {
        int prodId = cartAddDTO.getProductId();
        int cartId = cartAddDTO.getCartId();
        int quantity = cartAddDTO.getQuantity();
        CartDTO result = map.map(cartService.addToCart(prodId, quantity, cartId), CartDTO.class);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCart(@PathVariable Integer id,
                                     @RequestBody(required = false) CartDTO cartDTO,
                                     @RequestParam(required = false) Boolean removeAll) {
        if (cartDTO != null && removeAll == null) {
            Cart cart = map.map(cartDTO, Cart.class);
            CartDTO result = map.map(cartService.updateCart(id, cart), CartDTO.class);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        else if (cartDTO == null && removeAll == true) {
            cartService.removeCart(id);
            return new ResponseEntity(Map.of("message", "Remove cart successfully"), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{id}/update-cart-item")
    public ResponseEntity updateCartItem(@RequestBody(required = false) CartItemUpdateDTO cartItemUpdateDTO,
                                         @RequestParam(required = false) Integer rmCartItemId){
        if (rmCartItemId != null) {
            CartDTO result = map.map(cartService.removeCartItem(rmCartItemId), CartDTO.class);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        Integer cartItemId = cartItemUpdateDTO.getId();
        Integer quantity = cartItemUpdateDTO.getQuantity();
        CartDTO result = map.map(cartService.updateCartItemById(cartItemId, quantity), CartDTO.class);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
