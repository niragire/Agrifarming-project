package com.finalyearapp.agrifarming.controller;


import com.finalyearapp.agrifarming.helper.RegistrationRequest;
import com.finalyearapp.agrifarming.service.UserService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public RegistrationRequest userRegistrationDto() {
        return new RegistrationRequest();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") RegistrationRequest registrationDto) {
        try {
            userService.save(registrationDto);
        } catch (Exception e) {
            logger.error("An Error has occured while saving this user" + e);
        }

        return "redirect:/registration?success";
    }
    @GetMapping("/default")
    public String redirectAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/api/superAdmin";
        }else if(request.isUserInRole("USER")) {
            return "redirect:/";
        }else {
            return "redirect:/login";
        }

    }
}
