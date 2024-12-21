package com.java.user.model.request;

import com.java.user.model.Department;
import com.java.user.model.Gender;
import com.java.user.model.User;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record UserRequest(
        String name,         // 직원 성
        String email,        // 이메일 주소
        String phoneNumber,  // 전화번호
        Gender gender,       // 성별
        LocalDate hireDate,  // 고용일
        String jobTitle,     // 직책
        Department department // 부서
) {

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .hireDate(hireDate)
                .jobTitle(jobTitle)
                .department(department)
                .build();
    }




}
