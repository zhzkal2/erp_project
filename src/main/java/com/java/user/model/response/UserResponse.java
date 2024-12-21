package com.java.user.model.response;

import com.java.user.model.Department;
import com.java.user.model.Gender;
import com.java.user.model.User;
import java.time.LocalDate;

public record UserResponse(
        Long id,             // 직원 고유 ID
        String name,         // 직원 성
        String email,        // 이메일 주소
        String phoneNumber,  // 전화번호
        Gender gender,       // 성별
        LocalDate hireDate,  // 고용일
        String jobTitle,     // 직책
        Department department // 부서
) {

    public static UserResponse fromEntity(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getGender(),
                user.getHireDate(),
                user.getJobTitle(),
                user.getDepartment()
        );
    }

}
