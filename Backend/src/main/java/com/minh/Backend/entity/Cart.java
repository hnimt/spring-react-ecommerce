package com.minh.Backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "total", columnDefinition = "double default 0.0")
    private double total;

    @Column(name = "discount", columnDefinition = "double default 0.0")
    private double discount;

    @Column(name = "tax", columnDefinition = "double default 0.1")
    private double tax;

    @OneToOne(mappedBy = "cart")
    private User user;

    @OneToMany(mappedBy = "cart",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<CartItem> cartItems;

    public Cart(double total, double discount, double tax, User user) {
        this.total = total;
        this.discount = discount;
        this.tax = tax;
        this.user = user;
    }

    public Cart(double total, double discount, double tax) {
        this.total = total;
        this.discount = discount;
        this.tax = tax;
    }

    public void caclTotal() {
        total = 0;
        getCartItems().stream()
                .forEach(cartItem -> total+=cartItem.getTotal());
        total = total*(1+tax) - discount;
    }
}
