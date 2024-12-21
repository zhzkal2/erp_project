package com.java.user.model;

import com.java.config.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id; // 직원 고유 ID

    @Column(nullable = false)
    private String name; // 직원 성

    @Column(nullable = false, unique = true)
    private String email; // 이메일 주소

    @Column(nullable = false)
    private String phoneNumber; // 전화번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender; // 성별

    @Column(nullable = false)
    private LocalDate hireDate; // 고용일

    @Column(nullable = false)
    private String jobTitle; // 직책

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department; // 부서


}
