package com.grocery.app.Service.RegisterService;

import com.grocery.app.Dto.UserResponseDto;
import com.grocery.app.Model.User;

import java.util.Optional;

public interface RegisterUser {

    UserResponseDto create(User user);

    Optional<User> get(String id);
}
