package com.minh.Backend.dto.order;

import com.minh.Backend.dto.user.UserInfoDTO;
import lombok.Data;

@Data
public class OrderCreateDTO {
    private int userId;
    private String name;
    private String email;
    private String tel;
    private String address;
    private double subTotal;
    private double shipping;
    private double total;
}
