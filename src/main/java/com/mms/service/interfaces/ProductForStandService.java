package com.mms.service.interfaces;

import com.mms.dto.ProductForStand;

import java.util.List;

public interface ProductForStandService {

    List<ProductForStand> getProductsForStandList();
    void sendMessageToStandApp();
    void initMessage();

}
