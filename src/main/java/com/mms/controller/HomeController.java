package com.mms.controller;

import com.mms.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    //temporary fragment {
    private static Product product;

    static {
        product = new Product();
        product.setBrandName("Fiskars");
        product.setCategory("Camping");
        product.setColor("Silver");
        product.setDescription("brutal axe");
        product.setName("Axe");
        product.setSize(4);
        product.setWeight(3);
        product.setNumber(8);
    }

    // }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView myProfile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public ModelAndView catalog() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("catalog");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

}
