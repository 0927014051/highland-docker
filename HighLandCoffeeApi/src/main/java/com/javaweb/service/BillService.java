package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.Bill;
import com.javaweb.entity.Orders;

public interface BillService {
	Bill createBill(Orders orders, Long staff_id);
	
	List<Bill> findAll();
	
	
}
