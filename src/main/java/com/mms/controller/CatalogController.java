package com.mms.controller;

import com.mms.dto.*;
import com.mms.dto.converterDTO.*;
import com.mms.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.util.logging.LogManager;
import java.util.logging.Logger;


@Controller
@RequestMapping(value = "/catalog")
public class CatalogController {

    private static final Logger logger = Logger.getLogger(CatalogController.class.getName());

    private ProductService productService;
    private ProductInBascetService productInBascetService;
    private CategoryService categoryService;

    private int existingProductListPage;
    private int productInBascetListPage;

    private String temporaryProductName;
    private Boolean temporaryOnlyInStore;
    private Integer temporaryMinPrice;
    private Integer temporaryMaxPrice;
    private String temporaryNameOfCategory;

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
     * gets catalog with product and product in basket lists
     *
     * @param existingProductListPage current catalog list page
     * @param productInBascetListPage current basket page
     * @param catalogParam            determines successful / unsuccessful product addition
     * @param productName product name to filter catalog
     * @param onlyInStore "Only in store" param to filter catalog
     * @param minPrice product's min price to filter catalog
     * @param maxPrice product's max price to filter catalog
     * @param nameOfCategory category param to filter category
     * @return catalog with list of products and client's basket
     */
    @GetMapping
    public ModelAndView catalog(@RequestParam(name = "existingProductListPage", defaultValue = "1") int existingProductListPage,
                                @RequestParam(name = "productInBascetListPage", defaultValue = "1") int productInBascetListPage,
                                @RequestParam(name = "catalogParam", defaultValue = "standart") String catalogParam,
                                @RequestParam(name = "productName", required = false) String productName,
                                @RequestParam(name = "onlyInStore", required = false) Boolean onlyInStore,
                                @RequestParam(name = "minPrice", required = false) Integer minPrice,
                                @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
                                @RequestParam(name = "nameOfCategory", required = false) String nameOfCategory) {
        ModelAndView modelAndView = new ModelAndView();

        if (productName != null) temporaryProductName = productName;
        if (onlyInStore != null) temporaryOnlyInStore = onlyInStore;
        if (minPrice != null) temporaryMinPrice = minPrice -1;
        if (maxPrice != null) temporaryMaxPrice = maxPrice + 1;
        if (nameOfCategory != null) temporaryNameOfCategory = nameOfCategory;

        modelAndView.addObject("temporaryProductName", temporaryProductName);
        modelAndView.addObject("temporaryOnlyInStore", temporaryOnlyInStore);
        modelAndView.addObject("temporaryMinPrice", temporaryMinPrice);
        modelAndView.addObject("temporaryMaxPrice", temporaryMaxPrice);
        modelAndView.addObject("temporaryNameOfCategory", temporaryNameOfCategory);

        this.existingProductListPage = existingProductListPage;
        int productsCount = productService.getProductCountUsingFilter(temporaryProductName, temporaryOnlyInStore,
                temporaryMinPrice, temporaryMaxPrice, temporaryNameOfCategory);
        modelAndView.addObject("existingProductListPage", existingProductListPage);
        modelAndView.addObject("productList", productService.getAllProductsUsingFilter(existingProductListPage,
                temporaryProductName, temporaryOnlyInStore, temporaryMinPrice, temporaryMaxPrice, temporaryNameOfCategory));
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
     * shows product details
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
     * gets product edit-page
     *
     * @param id product id to edit it
     * @param uniqName uniq-name param (is uniq? 1 = true, 0 = false)
     * @returnproduct edit-page
     */
    @GetMapping(value = "/editProduct/{id}")
    public ModelAndView editPage(@PathVariable("id") int id,
                                 @RequestParam(name = "uniqName", defaultValue = "1") int uniqName) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/editProduct");

        modelAndView.addObject("uniqName", uniqName);
        modelAndView.addObject("existingProductListPage", existingProductListPage);
        modelAndView.addObject("productInBascetListPage", productInBascetListPage);
        modelAndView.addObject("product", productService.getProduct(id));
        modelAndView.addObject("categoryList", categoryService.getAllCategoriesWithoutPages());

        return modelAndView;
    }

    /**
     * edits product
     *
     * @param product        new Product object to edit existing
     * @param nameOfCategory chosen category for new product to edit existing
     * @return redirect to catalog oage after editing product
     */
    @PostMapping(value = "/editProduct")
    public String editProduct(@ModelAttribute("product") ProductDTO product,
                                    @RequestParam("nameOfCategory") String nameOfCategory) {

        product.setCategory(CategoryConverter.toEntity(categoryService.getCategoryByName(nameOfCategory)));

        // needs refactor
        try {
            logger.info("editing product with id = " + product.getId());
            productService.editProduct(product);
        }
        catch (DataIntegrityViolationException exc) {
            logger.info("fail edit product " + product.getId());
            return "redirect:/catalog/editProduct/" + product.getId() + "/?uniqName=0";
        }

        return "redirect:/catalog/?existingProductListPage=" + this.existingProductListPage + "&productInBascetListPage=" + productInBascetListPage;

    }

    /**
     * gets product add-page
     *
     * @return product add-page
     */
    @GetMapping(value = "/addProduct")
    public ModelAndView addPage(@RequestParam(name = "uniqName", defaultValue = "1") int uniqName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/editProduct");

        modelAndView.addObject("existingProductListPage", existingProductListPage);
        modelAndView.addObject("productInBascetListPage", productInBascetListPage);
        modelAndView.addObject("uniqName", uniqName);
        modelAndView.addObject("categoryList", categoryService.getAllCategoriesWithoutPages());

        return modelAndView;
    }

    /**
     * adds product to catalog
     *
     * @param product        new Product object
     * @param nameOfCategory chosen category for new product
     * @return return to catalog page after adding product
     */
    @PostMapping(value = "/add")
    public String addProduct(
            @ModelAttribute("product") ProductDTO product,
            @ModelAttribute(name = "nameOfCategory") String nameOfCategory) {

        product.setCategory(CategoryConverter.toEntity(categoryService.getCategoryByName(nameOfCategory)));

        // needs refactor
        try {
            logger.info("adding product " + product.getId());
            productService.addProduct(product);
        }
        catch (PersistenceException exc) {
            logger.info("fail adding product " + product.getId());
            return "redirect:/catalog/addProduct/?uniqName=0";
        }

        return "redirect:/catalog/?existingProductListPage=" + this.existingProductListPage +
                "&productInBascetListPage=" + productInBascetListPage;
    }

    /**
     * removes product from catalog
     *
     * @param id product id to delete it
     * @return redirect to catalog page after deleting
     */
    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + existingProductListPage +
                "&productInBascetListPage=" + productInBascetListPage);

        productService.deleteProduct(productService.getProduct(id));

        return modelAndView;
    }

    /**
     * removes product from basket
     *
     * @param id product in basket id to delete it
     * @return redirect to catalog page after deleting product from basket
     */
    @GetMapping(value = "/deleteProductInBascet/{id}")
    public ModelAndView deleteProductInBascet(@PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + existingProductListPage +
                "&productInBascetListPage=" + productInBascetListPage);

        productInBascetService.deleteProduct(productInBascetService.getProduct(id));

        return modelAndView;
    }

    /**
     * adds product to basket
     *
     * @param id                      product id to add it to basket
     * @param numberOfOrderedProducts number of adding products
     * @return redirect to catalog page after adding products in basket
     */
    @PostMapping(value = "/get/{id}")
    public ModelAndView getProductIntBascet(@PathVariable("id") int id,
                                            @RequestParam("quantity") int numberOfOrderedProducts) {
        ModelAndView modelAndView = new ModelAndView();

        logger.info("getting " + numberOfOrderedProducts + " products " + id);
        ProductInBascetDTO productInBascetDTO = new ProductInBascetDTO();
        productInBascetDTO.setProduct(ProductConverter.toEntity(productService.getProduct(id)));
        String catalogParam = productInBascetService.checkQuantityDifferenceThenAddProductInBascet(productInBascetDTO, numberOfOrderedProducts);

        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + existingProductListPage +
                "&productInBascetListPage=" + productInBascetListPage + "&catalogParam=" + catalogParam);

        return modelAndView;
    }

    /**
     * gets filter page
     *
     * @return
     */
    @GetMapping(value = "/catalogFilterPage")
    public ModelAndView getFilterPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/catalogFilterPage");

        modelAndView.addObject("existingProductListPage", existingProductListPage);
        modelAndView.addObject("productInBascetListPage", productInBascetListPage);

        modelAndView.addObject("categoryList", categoryService.getAllCategoriesWithoutPages());
        modelAndView.addObject("maxPrice", productService.getMaxProductPriceInStore());
        modelAndView.addObject("minPrice", productService.getMinProductPriceInStore());
        modelAndView.addObject("temporaryProductName", temporaryProductName);
        modelAndView.addObject("temporaryOnlyInStore", temporaryOnlyInStore);
        modelAndView.addObject("temporaryMinPrice", temporaryMinPrice);
        modelAndView.addObject("temporaryMaxPrice", temporaryMaxPrice);
        modelAndView.addObject("temporaryNameOfCategory", temporaryNameOfCategory);

        return modelAndView;
    }

    /**
     * shows filtered catalog
     *
     * @param productName product name to filter catalog
     * @param onlyInStore param to filter 0-quantity products
     * @param minPrice min price to filter catalog
     * @param maxPrice max price to filter catalog
     * @param nameOfCategory category param to filter catalog
     * @return filtered catalog page
     */
    @PostMapping(value = "/filterCatalog")
    public String useCatalogFilter(@RequestParam(name = "productName", required = false) String productName,
                                   @RequestParam(name = "onlyInStore", required = false) Boolean onlyInStore,
                                   @RequestParam(name = "minPrice", required = false) Integer minPrice,
                                   @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
                                   @RequestParam(name = "nameOfCategory", required = false) String nameOfCategory) {
        String redirect = "redirect:/catalog/?catalogParam=standart";

        logger.info("input filter params");
        if (productName != null) redirect += "&productName=" + productName;
        if (onlyInStore != null) redirect += "&onlyInStore=" + onlyInStore;
        else temporaryOnlyInStore = false;
        if (minPrice != null) redirect += "&minPrice=" + minPrice;
        if (maxPrice != null) redirect += "&maxPrice=" + maxPrice;
        if (nameOfCategory != null && !nameOfCategory.equals("All")) {
            redirect += "&nameOfCategory=" + nameOfCategory;
        }
        if (nameOfCategory != null) {
            if (nameOfCategory.equals("All")) {
                temporaryNameOfCategory = null;
            }
        }

        return redirect;
    }

    /**
     * resets filter
     *
     * @return reset filter
     */
    @GetMapping(value = "/resetFilter")
    public String resetFilter() {

        temporaryProductName = null;
        temporaryOnlyInStore = null;
        temporaryMinPrice = null;
        temporaryMaxPrice = null;
        temporaryNameOfCategory = null;

        return "redirect:/catalog";
    }

    @GetMapping(value = "/resetFilterAfterLogout")
    public String resetFilterAfterLogout() {

        temporaryProductName = null;
        temporaryOnlyInStore = null;
        temporaryMinPrice = null;
        temporaryMaxPrice = null;
        temporaryNameOfCategory = null;

        return "security/logoutSuccessPage";
    }


}
