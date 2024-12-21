package com.java.user.controller;

import com.java.user.model.request.UserRequest;
import com.java.user.model.response.UserResponse;
import com.java.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    //직원 등록
    @PostMapping("/save")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.saveUser(userRequest);
        return ResponseEntity.ok(response);
    }


    //직원 수정
//    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
//
//    }

    //직원 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    //직원 삭제(하드 딜리트)

}
