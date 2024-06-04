package com.javaweb.service;

import com.javaweb.entity.PriceUpdateDetail;
import com.javaweb.exception.ProductException;
import com.javaweb.exception.UserException;

public interface PriceUpdateService {
	
	PriceUpdateDetail findPriceUpdateByProductId(String productId);
	
	PriceUpdateDetail createPriceUpdate(String product_id,Long staff_id,int price_new);
	
	PriceUpdateDetail updatePrice(String productId,PriceUpdateDetail priceUpdateDetail, Long staff_id) throws ProductException, UserException;

}
