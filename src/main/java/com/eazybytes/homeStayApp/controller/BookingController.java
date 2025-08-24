package com.eazybytes.homeStayApp.controller;

import com.eazybytes.homeStayApp.model.Booking;
import com.eazybytes.homeStayApp.model.Person;
import com.eazybytes.homeStayApp.model.Room;
import com.eazybytes.homeStayApp.repository.PersonRepository;
import com.eazybytes.homeStayApp.service.BookingService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BookingService bookingService;

    @RequestMapping("")
    public String displayBookingPage(Model model, Authentication authentication, HttpSession httpSession){

       Person person=personRepository.getByEmail(authentication.getName());
       httpSession.setAttribute("loggedInPerson", person);
       Booking booking=new Booking();
       booking.setGuestName(person.getName());
       booking.setGuestMobileNum(person.getMobileNumber());
       booking.setGuestEmail(person.getEmail());
        model.addAttribute("booking",booking);
        return "booking.html";
    }


    @RequestMapping("/availableRooms")
    public String showAvailableRooms(@Valid @ModelAttribute("booking") Booking booking,
                                           Errors errors, Model model, HttpSession httpSession){
        if(booking.getEndDate().isBefore(booking.getStartDate())){
            errors.reject("Dates","End date cannot be before start date");
        }
        validateBooking(errors, httpSession);
        if(errors.hasErrors())
        {
            log.error("Invalid fields");
            return "booking";
        }

        List<Room> rooms = bookingService.findAvailableRooms(booking.getStartDate(),booking.getEndDate());
        log.info(rooms.toString());
        model.addAttribute("booking",booking);
        model.addAttribute("rooms",rooms);
// new room file
        return "rooms.html";
    }

    @RequestMapping(value = "/bookRooms",method = RequestMethod.POST)
    public String bookRoom(@ModelAttribute("booking") Booking booking, @RequestParam(value = "roomNos") List<Integer>  roomNos
            ,HttpSession httpSession){


        bookingService.saveBooking(booking,roomNos,httpSession);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/displayMyBookings")
    public String displayMyBookings(Model model, HttpSession session){

        Person person=(Person)session.getAttribute("loggedInPerson");
        model.addAttribute("person", person);

        return "myBookings.html";
    }

    private void validateBooking(Errors errors, HttpSession httpSession){

        Person person= (Person) httpSession.getAttribute("loggedInPerson");
        if(person.getBookings().size()>2)
            errors.reject("Limit reached","You can't have more that 3 active bookings at a time");
    }

}
