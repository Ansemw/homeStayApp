package com.eazybytes.homeStayApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller

public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout,
                                   @RequestParam(value = "register", required = false) String register,Model model){

        String errorMessage = null;
        if(error!=null){
            errorMessage = "User name or password is incorrect";
            System.out.println("we have an error" + errorMessage);
        }
        else if(logout!=null){
            errorMessage = "logged out successfully";
            System.out.println("we are logging out");
        }
       else if(register!=null){
            errorMessage = "User registered successfully";
            System.out.println("user registered");
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login.html";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOutPage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
        }
        return "redirect:/login?logout=true";
    }
}
