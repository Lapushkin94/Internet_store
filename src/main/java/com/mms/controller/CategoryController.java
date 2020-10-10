package com.mms.controller;

import com.mms.dto.CategoryDTO;
import com.mms.dto.ProductDTO;
import com.mms.service.interfaces.CategoryService;
import com.mms.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView getCategoriesControlPage(@RequestParam(name = "isEdited", defaultValue = "0") int isEdited) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/categoriesControlPage");

        modelAndView.addObject("isEdited", isEdited);
        modelAndView.addObject("categoryList", categoryService.getAllCategoriesWithoutPages());

        return modelAndView;
    }

    @PostMapping(value = "/editCategory/{categoryId}")
    public ModelAndView editCategory(@PathVariable("categoryId") int categoryId,
                                     @RequestParam("nameOfCategory") String newNameOfCategory) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/categories?isEdited=1");

        CategoryDTO categoryDTO = categoryService.getCategory(categoryId);
        categoryDTO.setNameOfCategory(newNameOfCategory);
        categoryService.editCategory(categoryDTO);

        return modelAndView;
    }

    @GetMapping(value = "/deleteCategory/{categoryId}")
    public ModelAndView getDeleteCategoryPage(@PathVariable("categoryId") int categoryId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/categories?isEdited=2");

        List<ProductDTO> productDTOS = productService.getAllProductsByCategoryId(categoryId);
        categoryService.changeCategoriesForProductList(productDTOS);
        categoryService.deleteCategory(categoryService.getCategory(categoryId));

        return modelAndView;
    }


    @PostMapping(value = "/addCategory")
    public ModelAndView addCategory(@ModelAttribute CategoryDTO categoryDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/categories?isEdited=3");

        categoryService.addCategory(categoryDTO);

        return modelAndView;
    }

}
