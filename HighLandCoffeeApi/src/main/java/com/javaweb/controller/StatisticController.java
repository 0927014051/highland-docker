package com.javaweb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.request.ProductSaleRequest;
import com.javaweb.request.StatisticRequest;
import com.javaweb.response.EntityStatusResponse;
import com.javaweb.response.ListEntityStatusResponse;
import com.javaweb.service.OrderService;
import com.javaweb.service.ProductService;

@RestController
@RequestMapping("/api/admin/statistic")
@SuppressWarnings({ "rawtypes", "unchecked" })

public class StatisticController {
	
	private ProductService productService;
	private OrderService orderService;
	
	

	public StatisticController(ProductService productService, OrderService orderService) {
		super();
		this.productService = productService;
		this.orderService = orderService;
	}

	@GetMapping("/product")
	public ResponseEntity<ListEntityStatusResponse> getStatisticProduct(){
		List<ProductSaleRequest> get = productService.getProductSales();
		ListEntityStatusResponse result = new ListEntityStatusResponse(get, HttpStatus.OK.value(), "success");
		return new ResponseEntity<ListEntityStatusResponse>(result,HttpStatus.OK);
	}
	
	@GetMapping("/year")
	public ResponseEntity<ListEntityStatusResponse> getStatisticOrder(@RequestParam String year){
		int changeYear = Integer.valueOf(year);
		List<StatisticRequest> rq = orderService.getTotalAmountByMonth(changeYear);
		ListEntityStatusResponse result = new ListEntityStatusResponse(rq, HttpStatus.OK.value(), "success");
		return new ResponseEntity<ListEntityStatusResponse>(result,HttpStatus.OK);
	}
	
	@GetMapping("/date")
	public ResponseEntity<ListEntityStatusResponse> getStatisticOrderByDate(@RequestParam("start") String dateStart, @RequestParam("end") String dateEnd){
	        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date start = null;
	        Date end = null;
	        try {
	            // Parsing a String to Date
	            start = dateFormatter.parse(dateStart);
	            end = dateFormatter.parse(dateEnd);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    
		List<ProductSaleRequest> rq = orderService.getTotalAmountByDate(start, end);
		ListEntityStatusResponse rs = new ListEntityStatusResponse<>(rq, HttpStatus.OK.value(), "success");
		return new ResponseEntity<ListEntityStatusResponse>(rs,HttpStatus.OK);
	}
}
