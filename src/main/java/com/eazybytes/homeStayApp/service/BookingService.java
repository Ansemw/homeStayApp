package com.eazybytes.homeStayApp.service;

import com.eazybytes.homeStayApp.constants.ProjectConstants;
import com.eazybytes.homeStayApp.model.Booking;
import com.eazybytes.homeStayApp.model.Person;
import com.eazybytes.homeStayApp.model.Room;
import com.eazybytes.homeStayApp.repository.BookingRepository;
import com.eazybytes.homeStayApp.repository.PersonRepository;
import com.eazybytes.homeStayApp.repository.RoomRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Getter
@Setter
@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RoomRepository roomRepository;

    public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate){

        List<Room> avaliableRooms= bookingRepository.findAvailableRooms(startDate,endDate);
        return avaliableRooms;
    }
    public Booking saveBooking(Booking booking,List<Integer> roomNos ,HttpSession httpSession){
        Person person= (Person) httpSession.getAttribute("loggedInPerson");

        booking.setPerson(person);
        booking.setStatus(ProjectConstants.BOOKING_CONFIRMED);
        booking.setRooms(findAllRooms(roomNos));
        //booking.setCreatedBy("Guest");
        //booking.setCreatedAt(LocalDateTime.now());
        person.getBookings().add(booking);
        httpSession.setAttribute("loggedInPerson", person);

        //booking.setPerson(personRepository.getByEmail("g@m.com"));
        return bookingRepository.save(booking);
    }

    private Set<Room> findAllRooms(List<Integer> roomNos) {
        Set<Room> rooms = new HashSet<>();
        roomNos.forEach(roomNo -> rooms.add(roomRepository.findById(roomNo).get()));
        return rooms; } //To change body of generated methods, choose Tools | Templates>
}
