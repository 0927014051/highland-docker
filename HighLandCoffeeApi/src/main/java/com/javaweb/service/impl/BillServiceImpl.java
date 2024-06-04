package com.javaweb.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.entity.Bill;
import com.javaweb.entity.Orders;
import com.javaweb.reponsitory.BillRepo;
import com.javaweb.service.BillService;

@Service
public class BillServiceImpl implements BillService{
	
	private BillRepo billRepo;

	public BillServiceImpl(BillRepo billRepo) {
		super(); 
		this.billRepo = billRepo;
	}
	
	@Override
	public Bill createBill(Orders orders,Long staff_id) {
		Bill createBill = new Bill();
		createBill.setCreated_at(LocalDateTime.now());
		createBill.setCreated_by(staff_id);
		createBill.setOrder_id(orders.getOrder_id());
		Bill saveBill = billRepo.save(createBill);
		return saveBill;
	}
	
	@Override
	public List<Bill> findAll(){
		return billRepo.findAll();
	}
	
}
