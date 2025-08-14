package com.example.backand.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Collection<? extends GrantedAuthority> roles;
}
