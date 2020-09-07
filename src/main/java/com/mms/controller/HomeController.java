package com.mms.controller;

import com.mms.model.Product;
import com.mms.service.MmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private MmsService mmsService;

    @Autowired
    public void setMmsService(MmsService mmsService) {
        this.mmsService = mmsService;
    }

    // home page, contains several links
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    // redirects to profile page
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView myProfile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    // redirects to products catalog page, will show all products and some links
    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public ModelAndView catalog(@RequestParam(defaultValue = "1") int page) {
        List<Product> productList = mmsService.allProducts(page);
        int productsCount = mmsService.productsCount();
        int pagesCount = (productsCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("catalog");
        modelAndView.addObject("page", page);
        modelAndView.addObject("allProducts", productList);
        modelAndView.addObject("productsCount", productsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        return modelAndView;
    }

    @RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
    public ModelAndView aboutUs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("aboutUs");
        return modelAndView;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView contacts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contacts");
        return modelAndView;
    }



}
