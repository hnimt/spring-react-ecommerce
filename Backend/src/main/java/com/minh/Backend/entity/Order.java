package com.minh.Backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "sub_total")
    private double subTotal;

    @Column(name = "shipping")
    private double shipping;

    @Column(name = "total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    public Order(double subTotal, double shipping, double total, User user) {
        this.subTotal = subTotal;
        this.shipping = shipping;
        this.total = total;
        this.user = user;
        this.orderDetails = new ArrayList<>();
    }
}
