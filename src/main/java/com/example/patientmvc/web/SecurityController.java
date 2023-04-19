package com.example.patientmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping(path = "/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }

    @GetMapping(path = "/login")
    public String login(){
        return "login";
    }
}
