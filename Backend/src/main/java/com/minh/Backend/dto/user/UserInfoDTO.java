package com.minh.Backend.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minh.Backend.dto.cart.CartDTO;
import com.minh.Backend.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor @AllArgsConstructor
public class UserInfoDTO {

    private int id;
    private String username;
    private String name;
    private String email;
    private String address;
    private String tel;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String token;
    private String role;
}
