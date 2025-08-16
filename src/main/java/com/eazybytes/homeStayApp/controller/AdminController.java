package com.eazybytes.homeStayApp.controller;

import com.eazybytes.homeStayApp.constants.ProjectConstants;
import com.eazybytes.homeStayApp.model.*;
import com.eazybytes.homeStayApp.repository.*;
import com.eazybytes.homeStayApp.service.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TrekRepository trekRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    TrekDetailsRepository trekDetailsRepository;


    @RequestMapping(value = "/displayAllBookings")
    public String displayMyBookings(Model model){

        List<Booking> bookings;
        bookings = bookingRepository.findAll();
        model.addAttribute("bookings", bookings);
        return "allBookings.html";
    }

    @RequestMapping(value = "/displayTreks")
    public String displayTreks(Model model, HttpSession session){

        List<Trek> treks;
        treks = trekRepository.findAll();
        model.addAttribute("treks", treks);
        return "treks.html";
    }
    @RequestMapping(value = "/activateTrek")
    public String activateTreks(Model model, @RequestParam int trekId){

        adminService.activateTrek(trekId);
        return "redirect:/admin/displayTreks";


    }

    @RequestMapping(value = "/deactivateTrek")
    public String deactivateTreks(Model model, @RequestParam int trekId){

        adminService.deactivateTrek(trekId);
        return "redirect:/admin/displayTreks";


    }
    @RequestMapping(value = "/viewTrekBookings")
    public String viewTrek(Model model, @RequestParam int trekId){

        List<Booking> bookings=bookingRepository.findActiveBookings(ProjectConstants.BOOKING_CONFIRMED);
        Trek trek=trekRepository.findById(trekId).get();
        List<TrekDetails> trekDetails=trekDetailsRepository.findByTrek(trek);

        model.addAttribute("bookings", bookings);
        model.addAttribute("trek", trek);
        model.addAttribute("trekDetails", trekDetails);
        return "trekBooker.html";


    }
    @RequestMapping(value = "/registerTrekBooking", method = RequestMethod.POST)
    public String registerTrekBooking(Model model, @RequestParam(name = "trekId") int trekId, @RequestParam(name = "bookingId") int bookingId,
                                      @RequestParam(name = "numPeople") int participants){

        adminService.registerTrekBooking(trekId, bookingId, participants);


        return "redirect:/admin/viewTrekBookings?trekId="+trekId;

    }

}
