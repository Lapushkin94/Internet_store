package com.mms.controller;

import com.mms.dto.*;
import com.mms.dto.converterDTO.*;
import com.mms.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;


@Controller
@RequestMapping(value = "/catalog")
@SessionAttributes("basketForSession")
public class CatalogController {

    private static final Logger logger = Logger.getLogger(CatalogController.class.getName());

    private ProductService productService;
    private BasketForSessionService basketForSessionService;
    private CategoryService categoryService;
    private ClientService clientService;
    private ProductForStandService productForStandService;
    private OrderService orderService;
    private OrderStatusService orderStatusService;

    private int existingProductListPage;
    private int productInBascetListPage;

    private String temporaryProductName;
    private Boolean temporaryOnlyInStore;
    private Integer temporaryMinPrice;
    private Integer temporaryMaxPrice;
    private String temporaryNameOfCategory;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
    private boolean refreshToOrderBlocker;

    @ModelAttribute
    public BasketForSession getBasketForSession() {
        return new BasketForSession();
    }

    @Autowired
    public void setOrderStatusService(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setProductForStandService(ProductForStandService productForStandService) {
        this.productForStandService = productForStandService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setBasketForSessionService(BasketForSessionService basketForSessionService) {
        this.basketForSessionService = basketForSessionService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
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
     * @param productName             product name to filter catalog
     * @param onlyInStore             "Only in store" param to filter catalog
     * @param minPrice                product's min price to filter catalog
     * @param maxPrice                product's max price to filter catalog
     * @param nameOfCategory          category param to filter category
     * @return catalog with list of products and client's basket
     */
    @GetMapping
    public ModelAndView catalog(@ModelAttribute BasketForSession basketForSession,
                                @RequestParam(name = "existingProductListPage", defaultValue = "1") int existingProductListPage,
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
        if (minPrice != null) temporaryMinPrice = minPrice - 1;
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
        int productsInBascetCount = basketForSession.getProductsInBasket().size();
        modelAndView.addObject("productInBascetListPage", productInBascetListPage);

        modelAndView.addObject("productInBascetList", basketForSessionService
                .getTemporaryBasketByPage(productInBascetListPage, basketForSession.getProductsInBasket()));
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
     * @param id       product id to edit it
     * @param uniqName uniq-name param (is uniq? 1 = true, 0 = false)
     * @return product edit-page
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
     * @return redirect to catalog page after editing product
     */
    @PostMapping(value = "/editProduct")
    public String editProduct(@ModelAttribute("product") ProductDTO product,
                              @RequestParam("nameOfCategory") String nameOfCategory) {

        product.setCategory(CategoryConverter.toEntity(categoryService.getCategoryByName(nameOfCategory)));

        String uniqNameStatus = productService.editProductAndReturnNameStatus(product);

        if (!uniqNameStatus.equals("okStatus")) return uniqNameStatus;

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

        String uniqNameStatus = productService.addProductAndReturnUnicNameStatus(product);

        if (!uniqNameStatus.equals("okStatus")) return uniqNameStatus;

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
    public ModelAndView deleteProductInBascet(@ModelAttribute BasketForSession basketForSession,
                                              @PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + existingProductListPage +
                "&productInBascetListPage=" + productInBascetListPage);

        basketForSession.setProductsInBasket(
                basketForSessionService
                        .removeProductFromBasketByIdAndReturnActualBasket(basketForSession.getProductsInBasket(), id));

        return modelAndView;
    }

    /**
     * adds product to basket
     *
     * @param productId               product id to add it to basket
     * @param numberOfOrderedProducts number of adding products
     * @return redirect to catalog page after adding products in basket
     */
    @PostMapping(value = "/get/{id}")
    public ModelAndView getProductIntBascet(@ModelAttribute BasketForSession basketForSession,
                                            @PathVariable("id") int productId,
                                            @RequestParam(name = "quantity", defaultValue = "1") int numberOfOrderedProducts) {
        ModelAndView modelAndView = new ModelAndView();

        logger.info("getting " + numberOfOrderedProducts + " products " + productId);
        Map<String, Map<Integer, ProductInBasketForSession>> result =
                basketForSessionService.calculateQuantityDifferenceToGetProductAndReturnCatalogParam(productId, numberOfOrderedProducts, basketForSession.getProductsInBasket());
        String catalogParam;
        if (result.containsKey("catalogSuccess")) {
            basketForSession.setProductsInBasket(result.get("catalogSuccess"));
            catalogParam = "catalogSuccess";
        } else catalogParam = "catalogFail";

        modelAndView.setViewName("redirect:/catalog/?existingProductListPage=" + existingProductListPage +
                "&productInBascetListPage=" + productInBascetListPage + "&catalogParam=" + catalogParam);

        return modelAndView;
    }

    /**
     * gets filter page
     *
     * @return filter page with parameters
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
     * @param productName    product name to filter catalog
     * @param onlyInStore    param to filter 0-quantity products
     * @param minPrice       min price to filter catalog
     * @param maxPrice       max price to filter catalog
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

    @GetMapping(value = "/resetBasket")
    public String resetBasket(@ModelAttribute BasketForSession basketForSession) {

        Map<Integer, ProductInBasketForSession> currentBasket = basketForSession.getProductsInBasket();
        currentBasket.clear();
        basketForSession.setProductsInBasket(currentBasket);

        return "redirect:/catalog/?existingProductListPage=" + existingProductListPage;
    }

    /**
     * first step to create order
     *
     * @param basketForSession        client's basket
     * @param productInBascetListPage basket page
     * @return basket full table
     */
    @GetMapping(value = "/order")
    public ModelAndView getBascet(@ModelAttribute BasketForSession basketForSession,
                                  @RequestParam(defaultValue = "1") int productInBascetListPage) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/checkBascetBeforeRegistration");

        this.productInBascetListPage = productInBascetListPage;
        int productsInBascetCount = basketForSession.getProductsInBasket().size();
        Map<String, Integer> basketParams = basketForSessionService.getSumAndQuantity(basketForSession.getProductsInBasket());

        modelAndView.addObject("sumPrice", basketParams.get("sum"));
        modelAndView.addObject("sumQuantity", basketParams.get("quantity"));
        modelAndView.addObject("productInBascetListPage", productInBascetListPage);
        modelAndView.addObject("productInBascetList", basketForSessionService
                .getTemporaryBasketByPage(productInBascetListPage, basketForSession.getProductsInBasket()));
        modelAndView.addObject("productsInBascetCount", productsInBascetCount);
        modelAndView.addObject("productInBascetPagesCount", (productsInBascetCount + 9) / 10);

        return modelAndView;
    }


    @GetMapping(value = "/order/orderRegistrationPage")
    public ModelAndView getOrderRegistrationPage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/orderRegistrationPage");

        refreshToOrderBlocker = false;
        modelAndView.addObject("client", clientService.getClientByEmail(user.getUsername()));

        return modelAndView;
    }

    /**
     * creating order and getting client info
     *
     * @param basketForSession client's basket
     * @param orderDTO         order object
     * @param clientAddressDTO client address object
     * @param user             current client
     * @return creating order result
     */
    @PostMapping(value = "/order/confirmation")
    public String doOrderRegistration(@ModelAttribute BasketForSession basketForSession,
                                      @ModelAttribute("order") OrderDTO orderDTO,
                                      @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO,
                                      @AuthenticationPrincipal User user) {

        if (refreshToOrderBlocker) return "redirect:/catalog/order";
        refreshToOrderBlocker = true;

        ClientDTO clientDTO = clientService.getClientByEmail(user.getUsername());
        clientDTO.setClientAddress(ClientAddressConverter.toEntity(clientAddressDTO));
        clientDTO.setActive(true);
        logger.info("getting order info " + orderDTO.getId());
        clientService.editClient(clientDTO);

        orderDTO.setId(0);
        orderDTO.setClient(ClientConverter.toEntity(clientDTO));
        orderDTO.setOrderStatus(OrderStatusConverter.toEntity(orderStatusService.getOpenedStatus()));
        orderDTO.setDate(dateFormat.format(new Date()));

        int orderId = orderService.addOrderAndReturnId(orderDTO);

        String result = orderService.createOrderAndReturnResult(basketForSession.getProductsInBasket(), orderId);

        if (result.equals("NotEnoughProducts")) {
            return "order/notEnoughProducts";
        }

        if (!result.equals("completedSuccessfully")) {
            return "order/somethingWrong";
        }

        basketForSession.setProductsInBasket(basketForSessionService.resetBasket(basketForSession.getProductsInBasket()));

        if (orderDTO.getPayStatus().equals("Card online")) {
            return "order/payment";
        }

        productForStandService.sendMessageToStandApp();

        return "order/congratulationsPage";
    }

    @PostMapping(value = "/order/successfulPayment")
    public ModelAndView getSuccessfulPaymentPage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/successfulPayment");

        productForStandService.sendMessageToStandApp();
        modelAndView.addObject("clientName", clientService.getClientByEmail(user.getUsername()).getName());

        return modelAndView;
    }

    /**
     * page to repeat client's order
     *
     * @param basketForSession client's basket
     * @param orderId          complete order id
     * @return created basket of products by repeating order
     */
    @GetMapping(value = "/repeatOrder/{id}")
    public ModelAndView repeatOrderById(@ModelAttribute BasketForSession basketForSession,
                                        @PathVariable("id") int orderId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/orderBasketForRepeat");

        basketForSession.setProductsInBasket(basketForSessionService.resetBasket(basketForSession.getProductsInBasket()));

        basketForSession.setProductsInBasket(basketForSessionService.addAllOrdersProductsToBasket(orderService.getProductsToAddByOrderId(orderId)));

        modelAndView.addObject("quantityDifferenceOfNotAddedProducts", basketForSessionService.getQuantityDifferenceForSecondaryOrderedProducts(orderService.getProductsToAddByOrderId(orderId)));

        modelAndView.addObject("editedProducts", orderService.getEditedProductsByOrderId(orderId));
        modelAndView.addObject("missingProducts", orderService.getMissingProductsByOrderId(orderId));

        modelAndView.addObject("productInBascetList", basketForSession.getProductsInBasket());
        modelAndView.addObject("sumPrice",
                basketForSessionService.getSumAndQuantity(basketForSession.getProductsInBasket()).get("sum"));
        modelAndView.addObject("sumQuantity",
                basketForSessionService.getSumAndQuantity(basketForSession.getProductsInBasket()).get("quantity"));

        return modelAndView;
    }

}
