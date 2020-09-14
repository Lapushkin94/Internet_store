package com.mms.controller;

import com.mms.dto.ProductDTO;
import com.mms.dto.ProductDetailsDTO;
import com.mms.dto.ProductInBascetDTO;
import com.mms.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.mms.dto.converter.ProductDetailsConverter.toEntity;

@Controller
@RequestMapping(value = "/catalog")
public class CatalogController {

    private ProductService productService;
    private ProductInBascetService productInBascetService;
    private OrderService orderService;
    private ClientService clientService;
    private CategoryService categoryService;

    private int existingProductListPage;
    private int productInBascetListPage;
    private int orderListPage;
    private int clientListPage;
    private int categoryListPage;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductInBascetService(ProductInBascetService productInBascetService) {
        this.productInBascetService = productInBascetService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * @param productListPage
     * @return
     */
    @GetMapping
    public ModelAndView catalog(@RequestParam(defaultValue = "1") int productListPage,
                                @RequestParam(defaultValue = "1") int productInBascetListPage) {
        List<ProductDTO> productList = productService.getAllExistingProducts(productListPage);
        this.existingProductListPage = productListPage;
        int productsCount = productService.getProductCount();
        int productPagesCount = (productsCount + 9) / 10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("catalog");
        modelAndView.addObject("productListPage", productListPage);
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("productsCount", productsCount);
        modelAndView.addObject("productPagesCount", productPagesCount);

        List<ProductInBascetDTO> productInBascetList = productInBascetService.getAllProductsInBascet(productInBascetListPage);
        this.productInBascetListPage = productInBascetListPage;
        int productsInBascetCount = productInBascetService.getProductCount();
        int productInBascetPagesCount = (productsInBascetCount + 9) / 10;
        modelAndView.addObject("productInBascetListPage", productInBascetListPage);
        modelAndView.addObject("productInBascetList", productInBascetList);
        modelAndView.addObject("productsInBascetCount", productsInBascetCount);
        modelAndView.addObject("productInBascetPagesCount", productInBascetPagesCount);

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
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage);
        productService.editProduct(product);
        return modelAndView;
    }

    // redirect to add-page (page for adding products)
    @GetMapping(value = "/addProduct")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProduct");
        return modelAndView;
    }

    // allows to add a new product and productDetails
    @PostMapping(value = "/add")
    public ModelAndView addProduct(
            @ModelAttribute("product") ProductDTO product,
            @ModelAttribute("productDetails") ProductDetailsDTO productDetails) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage);
        product.setProductDetails(toEntity(productDetails));
        productService.addProduct(product);
        return modelAndView;
    }

    // deletes chosen product
    // try DeleteMapping
    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage);
        productService.deleteProduct(productService.getProduct(id));
        return modelAndView;
    }

}
