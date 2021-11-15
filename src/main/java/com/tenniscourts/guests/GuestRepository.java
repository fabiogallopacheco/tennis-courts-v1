package com.tenniscourts.guests;

import com.tenniscourts.schedules.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query(value = "SELECT g FROM Guest g WHERE g.name like %:name% order by name")
    List<Guest> findByName(@Param("name") String name);

}
