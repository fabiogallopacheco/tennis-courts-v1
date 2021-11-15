package com.tenniscourts.schedules;

import com.tenniscourts.reservations.Reservation;
import com.tenniscourts.reservations.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT s FROM Schedule s JOIN s.tennisCourt t WHERE t.id = :id")
    List<Schedule> findByTennisCourt_IdOrderByStartDateTime(@Param("id") Long id);

    @Query(value = "SELECT s FROM Schedule s WHERE s.startDateTime BETWEEN :startDateTime and :endDateTime")
    List<Schedule> findSchedulesByDates(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);
}