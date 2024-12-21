package com.java.user.service;

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
        return UserResponse.fromEntity(userRepository.save(userRequest.toEntity()));

    }

    public UserResponse getUserById(Long id) {

        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(
                        () -> new UserNotFoundException("User with ID " + id + " does not exist."));

    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        return userRepository.findById(id)
                .map(user -> {
                    // 사용자 정보를 업데이트
                    User updatedUser = user.builder()
                            .name(userRequest.name())
                            .email(userRequest.email())
                            .phoneNumber(userRequest.phoneNumber())
                            .gender(userRequest.gender())
                            .hireDate(userRequest.hireDate())
                            .jobTitle(userRequest.jobTitle())
                            .department(userRequest.department())
                            .build();

                    // 업데이트된 사용자 저장
                    userRepository.save(updatedUser);

                    // 업데이트된 사용자 정보를 반환
                    return UserResponse.fromEntity(updatedUser);
                })
                .orElseThrow(
                        () -> new UserNotFoundException("User with ID " + id + " does not exist."));


    }

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with ID " + id + " does not exist."));
        userRepository.delete(user);
    }


}
