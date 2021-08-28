package com.minh.Backend.dto.user;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String username;
    private String password;
    private String role;
}
