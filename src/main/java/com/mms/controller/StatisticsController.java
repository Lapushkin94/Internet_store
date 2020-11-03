package com.mms.controller;

import com.mms.dto.ProductForStand;
import com.mms.service.interfaces.ProductForStandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@ResponseBody
public class StatisticsController {

    private ProductForStandService productForStandService;
    private Logger logger = Logger.getLogger(StatisticsController.class.getName());

    @Autowired
    public void setProductForStandService(ProductForStandService productForStandService) {
        this.productForStandService = productForStandService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/showProductStand")
    public List<ProductForStand> getProductsForStand() {

        logger.info("getting products for stand");
        logger.info(productForStandService.getProductsForStandList().toString());

        return productForStandService.getProductsForStandList();
    }

}
