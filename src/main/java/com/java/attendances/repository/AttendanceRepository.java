package com.java.attendances.repository;

import com.java.attendances.model.Attendance;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Page<Attendance> findByUserId(Long userId, Pageable pageable);
    Page<Attendance> findByUserIdAndCheckInTimeBetween(Long userId, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

}
