package com.eazybytes.homeStayApp.controller;


import com.eazybytes.homeStayApp.model.Holiday;
import com.eazybytes.homeStayApp.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HolidayController {


    public final HolidayRepository holidayRepository;

    @Autowired
    public HolidayController(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model) {
        if(display!=null){
            if(display.equals("all")){
                model.addAttribute("festival",true);
                model.addAttribute("maintenance",true);
            }
            else if(display.equals("federal"))  model.addAttribute("federal",true);

            else if(display.equals("maintenance")) model.addAttribute("maintenance",true);
        }
        Iterable<Holiday> holidays = holidayRepository.findAll();

        /* findAll() return all records from the table of entity
         class Holiday. The records are processed as type Iterable in java. To maintain the code's integrity, the iterable
         needs to be converted into a list*/
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(),false).toList();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}