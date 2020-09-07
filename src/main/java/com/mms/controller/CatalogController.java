package com.mms.controller;

import com.mms.model.Product;
import com.mms.model.ProductDetails;
import com.mms.service.MmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/catalog")
public class CatalogController {

    private MmsService mmsService;

    @Autowired
    public void setMmsService(MmsService mmsService) {
        this.mmsService = mmsService;
    }

    // shows detail information about chosen product
    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public ModelAndView getDetails(@PathVariable("id") int id) {
        Product product = mmsService.getById(id);
        ProductDetails productDetails = product.getProductDetails();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetails");
        modelAndView.addObject("product", product);
        modelAndView.addObject("productDetails", productDetails);
        return modelAndView;
    }

    // redirects to edit-page of chosen product
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Product product = mmsService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    // allows edit chosen product
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editProduct(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog");
        mmsService.edit(product);
        return modelAndView;
    }

    // redirect to add-page
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    // allows to add a new product
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addProduct(
            @ModelAttribute("product") Product product,
            @ModelAttribute("productDetails")ProductDetails productDetails) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog");
        product.setProductDetails(productDetails);
        mmsService.add(product);
        return modelAndView;
    }

    // deletes chosen product
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog");
        Product product = mmsService.getById(id);
        mmsService.delete(product);
        return modelAndView;
    }

}
