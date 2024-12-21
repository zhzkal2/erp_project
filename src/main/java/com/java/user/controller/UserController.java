package com.java.user.controller;

import com.java.user.model.request.UserRequest;
import com.java.user.model.response.UserResponse;
import com.java.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 새로운 사용자를 저장합니다.
     *
     * @param userRequest 사용자 요청
     * @return 사용자 응답을 포함한 ResponseEntity
     */

    @PostMapping("/save")
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.saveUser(userRequest);
        //이메일 중복 체크
        return ResponseEntity.ok(response);
    }

    /**
     * 기존 사용자를 업데이트합니다.
     *
     * @param id          사용자 ID
     * @param userRequest 사용자 요청
     * @return 사용자 응답을 포함한 ResponseEntity
     */
    @PatchMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
            @RequestBody UserRequest userRequest) {
        UserResponse response = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * ID로 사용자를 조회합니다.
     *
     * @param id 사용자 ID
     * @return 사용자 응답을 포함한 ResponseEntity
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * ID로 사용자를 삭제합니다.
     *
     * @param id 사용자 ID
     * @return 내용이 없는 ResponseEntity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
