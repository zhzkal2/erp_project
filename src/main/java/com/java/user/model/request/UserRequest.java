package com.java.user.model.request;

import com.java.user.model.Department;
import com.java.user.model.Gender;
import com.java.user.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Builder;


@Builder
public record UserRequest(
        @NotBlank String name,
        @Email @NotBlank String email,
        @NotBlank String phoneNumber,
        @NotNull Gender gender,
        @NotNull LocalDate hireDate,
        @NotBlank String jobTitle,
        @NotNull Department department
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
