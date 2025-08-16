package com.eazybytes.homeStayApp.repository;

import com.eazybytes.homeStayApp.model.Booking;
import com.eazybytes.homeStayApp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
/*
    @Query("SELECT r FROM room r WHERE r.roomNo NOT IN ( SELECT b.roomNo FROM Booking b WHERE b.startDate < ?1 AND b.endDate > ?2)")
    List<Room> findAvailableRooms( LocalDate startDate,  LocalDate endDate);
*/
@Query("SELECT r FROM Room r " +
        "WHERE r.roomNo NOT IN (" +
        "   SELECT br.roomNo FROM Booking b " +
        "   JOIN b.rooms br " +
        "   WHERE b.startDate < :endDate AND b.endDate > :startDate" +
        ")")
List<Room> findAvailableRooms(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    @Query("select b from Booking b where b.status = :status")
    List<Booking> findActiveBookings(@Param("status") String status);

}
