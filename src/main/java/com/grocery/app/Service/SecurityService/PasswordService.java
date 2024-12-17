package com.grocery.app.Service.SecurityService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}
