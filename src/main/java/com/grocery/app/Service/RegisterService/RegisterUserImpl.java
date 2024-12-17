package com.grocery.app.Service.RegisterService;

import com.grocery.app.Dto.UserResponseDto;
import com.grocery.app.Exception.ResourceNotCreatedException;
import com.grocery.app.Exception.ResourceNotFoundException;
import com.grocery.app.Model.User;
import com.grocery.app.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**This service allows user to register on our grocery app with their details.*/
@Service
public class RegisterUserImpl implements RegisterUser {

    UserRepository userRepository;

    public RegisterUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto create(User user) {
        try {
            userRepository.save(user);
            return UserResponseDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .created_at(user.getCreated_at())
                    .build();
        } catch (Exception exception){
            throw new ResourceNotCreatedException("Failed to register :" + exception.getMessage());
        }
    }

    @Override
    public Optional<User> get(String id){
        try {
            Optional<User> user = userRepository.findById(Integer.valueOf(id));
            return user;
        } catch (Exception exception){
            throw new ResourceNotFoundException("No user found :" + exception.getMessage());
        }
    }
}
