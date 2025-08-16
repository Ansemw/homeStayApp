package com.eazybytes.homeStayApp.controller;

import com.eazybytes.homeStayApp.model.Person;
import com.eazybytes.homeStayApp.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    public Environment environment;

    @Value("${example.pagesize}")
    public int pageSize;

    @Value("${example.message}")
    public String contactMessage;

    @RequestMapping("/dashboard")
    public String displayDashBoard(Model model, Authentication authentication, HttpSession httpSession) {


        Person person = personRepository.getByEmail(authentication.getName());


        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        httpSession.setAttribute("loggedInPerson", person);

        return "dashboard.html";
    }
}
