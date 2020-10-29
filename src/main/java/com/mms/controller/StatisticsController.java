package com.mms.controller;

import com.mms.dto.ProductForStand;
import com.mms.service.interfaces.ProductForStandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import java.util.List;
import java.util.logging.Logger;


@RestController(value = "/showProductStand")
public class StatisticsController {

    private ProductForStandService productForStandService;
    private Logger logger = Logger.getLogger(StatisticsController.class.getName());

    @Autowired
    public void setProductForStandService(ProductForStandService productForStandService) {
        this.productForStandService = productForStandService;
    }

    @GetMapping
    public List<ProductForStand> getProductsForStand() {

        logger.info("getting products for stand");
        logger.info(productForStandService.getProductsForStandList().toString());

        return productForStandService.getProductsForStandList();
    }

    //    @GET
//    public Response getProductsForStand() {
//
//        logger.info("getting products for stand");
//        logger.info(productForStandService.getProductsForStandList().toString());
//
//        return Response.ok().entity(productForStandService.getProductsForStandList()).build();
//    }

}
