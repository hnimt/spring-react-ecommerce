package com.minh.Backend.service.impl;

import com.minh.Backend.dto.order.OrderCreateDTO;
import com.minh.Backend.entity.*;
import com.minh.Backend.exception.ResourceNotFoundException;
import com.minh.Backend.repository.OrderRepository;
import com.minh.Backend.service.IOrderService;
import com.minh.Backend.service.MailService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Collection;

@Service
public class OrderService implements IOrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserService userService;
    @Autowired private ProductService productService;
    @Autowired private OrderDetailService orderDetailService;
    @Autowired private CartService cartService;
    @Autowired private MailService mailService;

    @Autowired private ModelMapper mapper;

    @Override
    public Collection<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find this id" + id));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Integer id) {
        orderRepository.deleteById(id);
    }

    @Transactional(rollbackOn = { SQLException.class })
    public void createOrder(OrderCreateDTO orderCreateDTO) {
        User user = updateUser(orderCreateDTO);
        Cart cart = cartService.findById(orderCreateDTO.getUserId());
        Order order = save(new Order(orderCreateDTO.getSubTotal(),
                orderCreateDTO.getShipping(), orderCreateDTO.getTotal(),
                user));
        cart.getCartItems().stream().forEach(cartItem -> {
            OrderDetail orderDetail = new OrderDetail(
                    cartItem.getQuantity(),
                    cartItem.getTotal(),
                    cartItem.getProduct(),
                    order
            );
            orderDetailService.save(orderDetail);
            order.getOrderDetails().add(orderDetail);
        });
        save(order);
        cartService.removeCart(cart.getId());
        mailService.sendMail(user, order);
    }

    private User updateUser(OrderCreateDTO orderCreateDTO) {
        User user = userService.findById(orderCreateDTO.getUserId());
        user.setName(orderCreateDTO.getName());
        user.setEmail(orderCreateDTO.getEmail());
        user.setTel(orderCreateDTO.getTel());
        user.setAddress(orderCreateDTO.getAddress());
        return userService.save(user);
    }


}
