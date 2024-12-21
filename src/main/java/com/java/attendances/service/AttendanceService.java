package com.java.attendances.service;

import com.java.attendances.model.Attendance;
import com.java.attendances.repository.AttendanceRepository;
import com.java.exception.AttendanceNotFoundException;
import com.java.exception.UserNotFoundException;
import com.java.user.model.User;
import com.java.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;


    public Attendance checkIn(Long userId, LocalDateTime checkInTime) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found with ID: " + userId, 404));

        Attendance attendance = Attendance.builder()
                .user(user)
                .checkInTime(checkInTime)
                .build();

        return attendanceRepository.save(attendance);
    }


    public Attendance checkOut(Long attendanceId, LocalDateTime checkOutTime) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new AttendanceNotFoundException(
                        "Attendance not found with ID: " + attendanceId, 404));

        attendance.setCheckOutTime(checkOutTime);
        return attendanceRepository.save(attendance);
    }

    public Page<Attendance> getAttendances(Long userId, Pageable pageable) {
        return attendanceRepository.findByUserId(userId, pageable);
    }

    public Page<Attendance> getAttendancesByDateRange(Long userId, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable) {
        return attendanceRepository.findByUserIdAndCheckInTimeBetween(userId, startDateTime, endDateTime, pageable);
    }

}
