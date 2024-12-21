package com.java.user.service;

import com.java.exception.UserNotFoundException;
import com.java.user.model.User;
import com.java.user.model.request.UserRequest;
import com.java.user.model.response.UserResponse;
import com.java.user.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserResponse saveUser(UserRequest userRequest) {
        return UserResponse.fromEntity(userRepository.save(userRequest.toEntity()));

    }
    public UserResponse getUserById(Long id) {

        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(()->new UserNotFoundException("User with ID " + id + " does not exist."));

    }

//    public UserResponse updateUser(UserRequest userRequest) {
//
//    }
//
//    public UserResponse deleteUser(UserRequest userRequest) {
//
//    }


}
