package com.eazybytes.homeStayApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

   @RequestMapping(value = {"","/","home"})
        public String displayHello(Model model)
   {
       model.addAttribute("username","Anurag Semwal");
       return "home.html";
        }

}
