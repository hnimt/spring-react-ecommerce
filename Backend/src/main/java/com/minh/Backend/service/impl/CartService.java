package com.minh.Backend.service.impl;

import com.minh.Backend.entity.Cart;
import com.minh.Backend.entity.CartItem;
import com.minh.Backend.entity.Product;
import com.minh.Backend.entity.User;
import com.minh.Backend.exception.OutOfStockException;
import com.minh.Backend.exception.ResourceNotFoundException;
import com.minh.Backend.repository.CartRepository;
import com.minh.Backend.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemService cartItemService;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(Integer id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot found cart id: " + id));
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void remove(Integer id) {
        cartRepository.deleteById(id);
    }

    public Cart createCart(double total, double discount, double tax, User user) {
        Cart cart = new Cart(total, discount, tax, user);
        return save(cart);
    }

    public Cart addToCart(int prodId, int quantity, int cartId) {
        Cart cart = findById(cartId);
        Product product = productService.findById(prodId);
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().getId() == product.getId())
                throw new OutOfStockException("Out of stock");
        }

        if (product.getStock() >= quantity) {
            product.setStock(product.getStock() - quantity);
            productService.save(product);
            CartItem cartItem = new CartItem(quantity, product, cart);
            cartItemService.save(cartItem);
            cart.getCartItems().add(cartItem);
            cart.caclTotal();
            return save(cart);
        }
        cart.caclTotal();
        return save(cart);
    }

    public Cart updateCart(Integer cartId, Cart cart) {
        Cart upCart = findById(cartId);
        List<CartItem> cartItems = cartItemService.findByCart(upCart);
        updateCartItems(cart, cartItems);
        upCart.setTotal(cart.getTotal());
        upCart.setDiscount(cart.getDiscount());
        upCart.setTax(cart.getTax());
        return upCart;
    }

    private void updateCartItems(Cart cart, List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            boolean existed = false;
            for (CartItem tmpCartItem : cart.getCartItems()) {
                if (cartItem.getId() == tmpCartItem.getId()) {
                    existed = true;
                    Product product = productService.findById(cartItem.getProduct().getId());
                    if (product.getStock() + cartItem.getQuantity() - tmpCartItem.getQuantity() < 0)
                        throw new OutOfStockException("Out of stock");

                    product.setStock(product.getStock() + cartItem.getQuantity() - tmpCartItem.getQuantity());
                    productService.save(product);
                    cartItem.setQuantity(tmpCartItem.getQuantity());
                    cartItemService.save(cartItem);
                    break;
                }
            }

            if (existed == false) {
                Product product = productService.findById(cartItem.getProduct().getId());
                product.setStock(product.getStock() + cartItem.getQuantity());
                productService.save(product);
                cartItemService.remove(cartItem.getId());
            }
        }
    }

    public Cart updateCartItemById(int cartItemId, int quantity) {
        CartItem cartItem = cartItemService.findById(cartItemId);
        Product product = cartItem.getProduct();
        if (product.getStock() + cartItem.getQuantity() - quantity < 0)
            throw new OutOfStockException("Out of stock");
        product.setStock(product.getStock() + cartItem.getQuantity() - quantity);
        productService.save(product);
        cartItem.setQuantity(quantity);
        cartItem.calcTotal();
        cartItemService.save(cartItem);
        Cart cart = cartItem.getCart();
        cart.caclTotal();
        return save(cart);
    }

    public void removeCart(Integer id) {
        Cart cart = findById(id);
        cart.getCartItems().clear();
        cart.caclTotal();
        save(cart);
    }

    public Cart removeCartItem(Integer rmCartItemId) {
        CartItem cartItem = cartItemService.findById(rmCartItemId);
        Product product = cartItem.getProduct();
        product.setStock(product.getStock() + cartItem.getQuantity());
        productService.save(product);
        Cart cart = cartItem.getCart();
        cartItemService.remove(rmCartItemId);
        cart.caclTotal();
        return save(cart);
    }
}
