package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.OrderDetail;

public interface OrderDetailService {
	List<OrderDetail> findOrderDetailByOrderIdAndProductId(Long order_id,String product_id);
	OrderDetail findFirstByProductProductIdOrderByOrderDetailIdAsc(String product_id);
}
