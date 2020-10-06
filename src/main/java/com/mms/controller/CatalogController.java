package com.mms.controller;

import com.mms.dto.*;
import com.mms.dto.converterDTO.*;
import com.mms.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/catalog")
public class CatalogController {

    private ProductService productService;
    private ProductInBascetService productInBascetService;
    private CategoryService categoryService;

    private int existingProductListPage;
    private int productInBascetListPage;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductInBascetService(ProductInBascetService productInBascetService) {
        this.productInBascetService = productInBascetService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     *
     * @param existingProductListPage current catalog list page
     * @param productInBascetListPage current basket page
     * @param catalogParam determines successful / unsuccessful product addition
     * @return catalog page with list of products and client's basket
     */
    @GetMapping
    public ModelAndView catalog(@RequestParam(defaultValue = "1") int existingProductListPage,
                                @RequestParam(defaultValue = "1") int productInBascetListPage,
                                @RequestParam(defaultValue = "standart") String catalogParam) {
        ModelAndView modelAndView = new ModelAndView();

        this.existingProductListPage = existingProductListPage;
        int productsCount = productService.getProductCount();
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

        modelAndView.addObject("categoryList", categoryService.getAllCategoriesWithoutPages());
        modelAndView.addObject("catalogParam", catalogParam);
        modelAndView.setViewName("product/catalog");

        return modelAndView;
    }

    /**
     *
     * @param id product id to get product details
     * @return product details-page
     */
    @GetMapping(value = "/productDetails/{id}")
    public ModelAndView getDetails(@PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/productDetails");
        modelAndView.addObject("existingProductListPage", existingProductListPage);
        modelAndView.addObject("productInBascetListPage", productInBascetListPage);
        modelAndView.addObject("product", productService.getProduct(id));

        return modelAndView;
    }

    /**
     *
     * @param id product id to edit it
     * @return product edit-page
     */
    @GetMapping(value = "/editProduct/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/editProduct");
        modelAndView.addObject("existingProductListPage", existingProductListPage);
        modelAndView.addObject("product", productService.getProduct(id));
        modelAndView.addObject("categoryList", categoryService.getAllCategoriesWithoutPages());

        return modelAndView;
    }

    /**
     *
     * @param product new Product object to edit existing
     * @param nameOfCategory chosen category for new product to edit existing
     * @return redirect to catalog oage after editing product
     */
    @PostMapping(value = "/editProduct")
    public ModelAndView editProduct(@ModelAttribute("product") ProductDTO product,
                                    @RequestParam("nameOfCategory") String nameOfCategory) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetLstPage=" + productInBascetListPage);
        product.setCategory(CategoryConverter.toEntity(categoryService.getCategoryByName(nameOfCategory)));

        productService.editProduct(product);

        return modelAndView;
    }

    /**
     *
     * @return product add-page
     */
    @GetMapping(value = "/addProduct")
    public ModelAndView addPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/editProduct");
        modelAndView.addObject("categoryList", categoryService.getAllCategoriesWithoutPages());

        return modelAndView;
    }

    /**
     *
     * @param product new Product object
     * @param nameOfCategory chosen category for new product
     * @return return to catalog page after adding product
     */
    @PostMapping(value = "/add")
    public ModelAndView addProduct(
            @ModelAttribute("product") ProductDTO product,
            @ModelAttribute(name = "nameOfCategory") String nameOfCategory) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetLstPage=" + productInBascetListPage);

        product.setCategory(CategoryConverter.toEntity(categoryService.getCategoryByName(nameOfCategory)));
        productService.addProduct(product);

        return modelAndView;
    }

    /**
     *
     * @param id product id to delete it
     * @return redirect to catalog page after deleting
     */
    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetLstPage=" + productInBascetListPage);

        productService.deleteProduct(productService.getProduct(id));

        return modelAndView;
    }

    /**
     *
     * @param id product in basket id to delete it
     * @return redirect to catalog page after deleting product from basket
     */
    @GetMapping(value = "/deleteProductInBascet/{id}")
    public ModelAndView deleteProductInBascet(@PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetLstPage=" + productInBascetListPage);

        productInBascetService.deleteProduct(productInBascetService.getProduct(id));

        return modelAndView;
    }

    /**
     *
     * @param id product id to add it to basket
     * @param numberOfOrderedProducts number of adding products
     * @return redirect to catalog page after adding products in basket
     */
    @PostMapping(value = "/get/{id}")
    public ModelAndView getProductIntBascet(@PathVariable("id") int id,
                                            @RequestParam("quantity") int numberOfOrderedProducts) {
        ModelAndView modelAndView = new ModelAndView();

        ProductInBascetDTO productInBascetDTO = new ProductInBascetDTO();
        productInBascetDTO.setProduct(ProductConverter.toEntity(productService.getProduct(id)));
        String catalogParam = productInBascetService.checkQuantityDifferenceThenAddProductInBascet(productInBascetDTO, numberOfOrderedProducts);


        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetListPage=" + productInBascetListPage + "&catalogParam=" + catalogParam);

        return modelAndView;
    }

    @GetMapping(value = "/catalogFilterPage")
    public ModelAndView getFilterPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/catalogFilterPage");



        return modelAndView;
    }


}
