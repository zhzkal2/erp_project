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
                    User updatedUser = user.toBuilder()  // 기존 유저 객체를 복사하고
                            .name(userRequest.name())
                            .email(userRequest.email())
                            .phoneNumber(userRequest.phoneNumber())
                            .jobTitle(userRequest.jobTitle())
                            .department(userRequest.department())
                            .build();

                    //이메일 중복체크
                    if (userRepository.existsByEmail(userRequest.email()) && !user.getEmail()
                            .equals(userRequest.email())) {
                        throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다.");
                    }
                    // 업데이트된 사용자 저장하고 반환
                    return UserResponse.fromEntity(userRepository.save(updatedUser));
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
