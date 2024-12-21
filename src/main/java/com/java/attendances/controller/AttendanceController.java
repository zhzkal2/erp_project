package com.java.attendances.controller;

import com.java.attendances.model.Attendance;
import com.java.attendances.service.AttendanceService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/checkin")
    public ResponseEntity<Attendance> checkIn(@RequestParam Long userId, @RequestParam LocalDateTime checkInTime) {
        Attendance attendance = attendanceService.checkIn(userId, checkInTime);
        return ResponseEntity.ok(attendance);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Attendance> checkOut(@RequestParam Long attendanceId, @RequestParam LocalDateTime checkOutTime) {
        Attendance attendance = attendanceService.checkOut(attendanceId, checkOutTime);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<Attendance>> getAttendances(@PathVariable Long userId, Pageable pageable) {
        Page<Attendance> attendances = attendanceService.getAttendances(userId, pageable);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/user/{userId}/range")
    public ResponseEntity<Page<Attendance>> getAttendancesByDateRange(
            @PathVariable Long userId,
            @RequestParam LocalDateTime startDateTime,
            @RequestParam LocalDateTime endDateTime,
            Pageable pageable) {
        Page<Attendance> attendances = attendanceService.getAttendancesByDateRange(userId, startDateTime, endDateTime, pageable);
        return ResponseEntity.ok(attendances);
    }

}
