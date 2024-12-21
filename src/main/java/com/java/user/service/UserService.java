package com.java.user.service;

import com.java.exception.EmailAlreadyExistsException;
import com.java.exception.UserNotFoundException;
import com.java.user.model.User;
import com.java.user.model.request.UserRequest;
import com.java.user.model.response.UserResponse;
import com.java.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse saveUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다.", 409);
        }
        return UserResponse.fromEntity(userRepository.save(userRequest.toEntity()));
    }

    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(
                        () -> new UserNotFoundException("User with ID " + id + " does not exist.",
                                404));
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        return userRepository.findById(id)
                .map(user -> {
                    validateEmail(userRequest.email(), user.getEmail());
                    User updatedUser = updateUserFields(user, userRequest);
                    return UserResponse.fromEntity(userRepository.save(updatedUser));
                })
                .orElseThrow(
                        () -> new UserNotFoundException("User with ID " + id + " does not exist.",
                                404));
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User with ID " + id + " does not exist.",
                                404));
        userRepository.delete(user);
    }

    private void validateEmail(String newEmail, String currentEmail) {
        if (userRepository.existsByEmail(newEmail) && !newEmail.equals(currentEmail)) {
            throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다.", 409);
        }
    }

    private User updateUserFields(User user, UserRequest userRequest) {
        return user.toBuilder()
                .name(userRequest.name())
                .email(userRequest.email())
                .phoneNumber(userRequest.phoneNumber())
                .jobTitle(userRequest.jobTitle())
                .department(userRequest.department())
                .build();
    }
}
