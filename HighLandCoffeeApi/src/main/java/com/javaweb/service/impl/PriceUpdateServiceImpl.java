package com.javaweb.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.javaweb.entity.PriceUpdateDetail;
import com.javaweb.entity.Product;
import com.javaweb.exception.ProductException;
import com.javaweb.exception.UserException;
import com.javaweb.reponsitory.PriceUpdateRepo;
import com.javaweb.service.PriceUpdateService;
import com.javaweb.service.ProductService;

@Service
public class PriceUpdateServiceImpl implements PriceUpdateService{

	private PriceUpdateRepo priceUpdateRepo;

	public PriceUpdateServiceImpl(PriceUpdateRepo priceUpdateRepo) {
		super();
		this.priceUpdateRepo = priceUpdateRepo;
	}
	
	@Override
	public PriceUpdateDetail createPriceUpdate(String product_id, Long staff_id, int price_new) {
		PriceUpdateDetail priceUpdateDetail = new PriceUpdateDetail();
		priceUpdateDetail.setCreated_at(LocalDateTime.now());
		priceUpdateDetail.setCreated_by(staff_id);
		priceUpdateDetail.setPrice_new(price_new);
		priceUpdateDetail.setPrice_old(price_new);
		priceUpdateDetail.setProduct_id(priceUpdateDetail.getProduct_id());
		priceUpdateDetail.setUpdated_at(LocalDateTime.now());
		return priceUpdateRepo.save(priceUpdateDetail);
	}
	
	@Override
	public PriceUpdateDetail updatePrice(String productId,PriceUpdateDetail rq, Long staff_id) throws ProductException, UserException {
		PriceUpdateDetail priceUpdateDetail = priceUpdateRepo.findPriceByProductId(productId);
		priceUpdateDetail.setPrice_old(priceUpdateDetail.getPrice_new());
		if(rq.getPrice_new() != 0) {
			priceUpdateDetail.setPrice_new(rq.getPrice_new());
			priceUpdateDetail.setUpdated_by(staff_id);
			priceUpdateDetail.setUpdated_at(LocalDateTime.now());
		}
		return priceUpdateRepo.save(priceUpdateDetail);
	}
	
	@Override
	public PriceUpdateDetail findPriceUpdateByProductId(String productId) {
		return priceUpdateRepo.findPriceByProductId(productId);
	}

	
	
}
