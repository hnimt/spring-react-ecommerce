package com.minh.Backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ADMIN"), USER("USER");
    private String userRole;
}
