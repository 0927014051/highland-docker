package com.javaweb.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.entity.OrderDetail;
import com.javaweb.reponsitory.OrderDetailRepo;
import com.javaweb.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    private OrderDetailRepo orderDetailRepo;

    

    public OrderDetailServiceImpl(OrderDetailRepo orderDetailRepo) {
        this.orderDetailRepo = orderDetailRepo;
    }

    @Override
    public List<OrderDetail> findOrderDetailByOrderIdAndProductId(Long order_id, String product_id) {
        return orderDetailRepo.findOrderDetailByOrderIdAndProductId(order_id, product_id);
    }

    @Override
    public OrderDetail findFirstByProductProductIdOrderByOrderDetailIdAsc(String product_id) {
        // TODO Auto-generated method stub
return orderDetailRepo.findFirstByProductProductIdOrderByOrderDetailIdAsc(product_id) ;
   }

}
