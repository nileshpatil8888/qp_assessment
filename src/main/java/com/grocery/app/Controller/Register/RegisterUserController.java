package com.grocery.app.Controller.Register;

import com.grocery.app.Dto.UserRequestDto;
import com.grocery.app.Dto.UserResponseDto;
import com.grocery.app.Model.User;
import com.grocery.app.Service.SecurityService.PasswordService;
import com.grocery.app.Service.RegisterService.RegisterUserImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/sign-in")
public class RegisterUserController {
    @Autowired
    RegisterUserImpl registerUser;
    @Autowired
    PasswordService passwordService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto user){
        String pasword = passwordService.hashPassword(user.getPassword_hash());
        Timestamp createdAt = Timestamp.from(Instant.now());
        User userdto = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password_hash(pasword)
                .role(user.getRole())
                .created_at(String.valueOf(createdAt))
                .build();
        var userResponse  = registerUser.create(userdto);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = registerUser.get(id).get();
        return ResponseEntity.ok(user);
    }
}
