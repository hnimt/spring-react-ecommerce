package com.minh.Backend.service.impl;

import com.minh.Backend.entity.OrderDetail;
import com.minh.Backend.exception.ResourceNotFoundException;
import com.minh.Backend.repository.OrderDetailRepository;
import com.minh.Backend.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired private OrderDetailRepository orderDetailRepository;

    @Override
    public Collection<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findById(Integer id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find this id"+ id));
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void remove(Integer id) {
        orderDetailRepository.deleteById(id);
    }
}
