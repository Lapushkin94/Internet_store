package com.mms.controller;

import com.mms.dto.*;
import com.mms.dto.converterDTO.CategoryConverter;
import com.mms.dto.converterDTO.OrderConverter;
import com.mms.dto.converterDTO.OrderStatusConverter;
import com.mms.dto.converterDTO.ProductConverter;
import com.mms.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

import static com.mms.dto.converterDTO.ProductDetailsConverter.toEntity;

@Controller
@RequestMapping(value = "/catalog")
public class CatalogController {

    private ProductService productService;
    private ProductInBascetService productInBascetService;
    private CategoryService categoryService;

//    private ClientService clientService;


    private int existingProductListPage;
    private int productInBascetListPage;
//    private int clientListPage;
//    private int categoryListPage;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductInBascetService(ProductInBascetService productInBascetService) {
        this.productInBascetService = productInBascetService;
    }


    //    @Autowired
//    public void setClientService(ClientService clientService) {
//        this.clientService = clientService;
//    }
//
    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView catalog(@RequestParam(defaultValue = "1") int existingProductListPage,
                                @RequestParam(defaultValue = "1") int productInBascetListPage) {
        this.existingProductListPage = existingProductListPage;
        int productsCount = productService.getProductCount();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("catalog");
        modelAndView.addObject("existingProductListPage", existingProductListPage);
        modelAndView.addObject("productList", productService.getAllExistingProducts(existingProductListPage));
        modelAndView.addObject("productsCount", productsCount);
        modelAndView.addObject("productPagesCount", (productsCount + 9) / 10);

        this.productInBascetListPage = productInBascetListPage;
        int productsInBascetCount = productInBascetService.getProductCount();
        modelAndView.addObject("productInBascetListPage", productInBascetListPage);
        modelAndView.addObject("productInBascetList", productInBascetService.getAllProductsInBascet(productInBascetListPage));
        modelAndView.addObject("productsInBascetCount", productsInBascetCount);
        modelAndView.addObject("productInBascetPagesCount", (productsInBascetCount + 9) / 10);

        return modelAndView;
    }

    // shows detail information about chosen product
    @GetMapping(value = "/productDetails/{id}")
    public ModelAndView getDetails(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetails");
        modelAndView.addObject("existingProductListPage", existingProductListPage);
        modelAndView.addObject("product", productService.getProduct(id));
        return modelAndView;
    }

    // redirects to edit-page of chosen product
    @GetMapping(value = "/editProduct/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProduct");
        modelAndView.addObject("existingProductListPage", existingProductListPage);
        modelAndView.addObject("product", productService.getProduct(id));
        return modelAndView;
    }

    // allows edit chosen product
    @PostMapping(value = "/editProduct")
    public ModelAndView editProduct(@ModelAttribute("product") ProductDTO product) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetLstPage=" + productInBascetListPage);
        productService.editProduct(product);

        // Необходима правильная обработка параметров quantity и category
        // Иначе ошибка 400

        return modelAndView;
    }

    // redirect to add-page (page for adding products)
    @GetMapping(value = "/addProduct")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProduct");
        modelAndView.addObject("categoryList", categoryService.getAllCategoriesWithoutPages());
        return modelAndView;
    }

    // allows to add a new product and productDetails
    @PostMapping(value = "/add")
    public ModelAndView addProduct(
            @ModelAttribute("product") ProductDTO product,
            @ModelAttribute("productDetails") ProductDetailsDTO productDetails,
            @ModelAttribute(name = "chosenCategory") CategoryDTO categoryDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetLstPage=" + productInBascetListPage);
        product.setProductDetails(toEntity(productDetails));
        product.setCategory(CategoryConverter.toEntity(categoryDTO));
        // ошибка save the transient instance before flushing: com.mms.model.CategoryEntity
        productService.addProduct(product);

        // Необходима правильная обработка параметра category
        // Иначе ошибка 400

        return modelAndView;
    }

    // deletes chosen product
    // try DeleteMapping
    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetLstPage=" + productInBascetListPage);
        productService.deleteProduct(productService.getProduct(id));
        return modelAndView;
    }

    @PostMapping(value = "/get/{id}")
    public ModelAndView getProductIntBascet(@PathVariable("id") int id,
                                            @RequestParam("quantity") int numberOfOrderedProducts) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetLstPage=" + productInBascetListPage);
        ProductDTO productDTO = productService.getProduct(id);

        ProductInBascetDTO productInBascetDTO = new ProductInBascetDTO();
        productInBascetDTO.setProduct(ProductConverter.toEntity(productDTO));
        // В jsp реализовать сравнение quantity продукта в корзине и quantity продукта в каталоге
        productInBascetDTO.setQuantity(numberOfOrderedProducts);

        for (ProductInBascetDTO prod : productInBascetService.getAllProductsInBascetWithoutPages()) {
            if (prod.getProduct().getId() == productInBascetDTO.getProduct().getId()) {
                prod.setQuantity(prod.getQuantity() + numberOfOrderedProducts);
                productInBascetService.editProduct(prod);
                return modelAndView;
            }
        }
        productInBascetService.addProduct(productInBascetDTO);
        return modelAndView;
    }

}
