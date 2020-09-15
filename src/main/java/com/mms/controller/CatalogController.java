package com.mms.controller;

import com.mms.dto.ProductDTO;
import com.mms.dto.ProductDetailsDTO;
import com.mms.dto.ProductInBascetDTO;
import com.mms.dto.converterDTO.ProductConverter;
import com.mms.dto.converterDTO.ProductInBascetConverter;
import com.mms.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.mms.dto.converterDTO.ProductDetailsConverter.toDto;
import static com.mms.dto.converterDTO.ProductDetailsConverter.toEntity;

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
     * @param existingProductListPage
     * @return
     */
    @GetMapping
    public ModelAndView catalog(@RequestParam(defaultValue = "1") int existingProductListPage,
                                @RequestParam(defaultValue = "1") int productInBascetListPage) {
        List<ProductDTO> productList = productService.getAllExistingProducts(existingProductListPage);
        this.existingProductListPage = existingProductListPage;
        int productsCount = productService.getProductCount();
        int productPagesCount = (productsCount + 9) / 10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("catalog");
        modelAndView.addObject("existingProductListPage", existingProductListPage);
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
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetLstPage=" + productInBascetListPage);
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
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetLstPage=" + productInBascetListPage);
        product.setProductDetails(toEntity(productDetails));
        productService.addProduct(product);
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
        productDTO.setQuantityInStore(productDTO.getQuantityInStore() - numberOfOrderedProducts);
        productService.editProduct(productDTO);

        ProductInBascetDTO productInBascetDTO = new ProductInBascetDTO();
        productInBascetDTO.setProduct(ProductConverter.toEntity(productDTO));
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
