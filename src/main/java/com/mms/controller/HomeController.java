package com.mms.controller;

import com.mms.service.interfaces.ProductInBascetService;
import com.mms.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    // home page, contains several links
    @GetMapping(value = "/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    // redirects to profile page
    @GetMapping(value = "/profile")
    public ModelAndView myProfile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    // some info about the shop
    @GetMapping(value = "/aboutUs")
    public ModelAndView aboutUs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("aboutUs");
        return modelAndView;
    }

    // company contacts (email, mobile number, etc.)
    @GetMapping(value = "/contacts")
    public ModelAndView contacts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contacts");
        return modelAndView;
    }


}
