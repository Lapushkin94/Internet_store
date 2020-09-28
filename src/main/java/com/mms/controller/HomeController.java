package com.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String homePage() {
        return "home";
    }

    @GetMapping(value = "/profile")
    public String myProfile() {
        return "profile";
    }

    @GetMapping(value = "/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }

    @GetMapping(value = "/contacts")
    public String contacts() {
        return "contacts";
    }

}
