package com.javaweb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javaweb.entity.Orders;
import com.javaweb.entity.Staff;
import com.javaweb.entity.User;
import com.javaweb.exception.ProductException;
import com.javaweb.exception.UserException;
import com.javaweb.response.ApiResponse;
import com.javaweb.response.EntityStatusResponse;
import com.javaweb.response.ListEntityStatusResponse;
import com.javaweb.service.OrderService;
import com.javaweb.service.StaffService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/admin/order")
@SuppressWarnings({ "rawtypes", "unchecked" })

public class AdminOrderController {
	
	private OrderService orderService;
	private UserService userService;
	private StaffService staffService;
	public AdminOrderController(OrderService orderService, UserService userService, StaffService staffService) {
		super();
		this.orderService = orderService;
		this.userService = userService;
		this.staffService = staffService;
	}
	
	@PutMapping("/{order_id}/status")
	public ResponseEntity<ApiResponse> updateStatusOrder(@PathVariable Long order_id,@RequestBody Orders orders, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user = userService.findUserByJwt(jwt);
		Staff staff = staffService.findStaffByUserId(user.getUser_id());
		System.err.println("status: " + orders.getStatus());

		Orders updateOrders = orderService.updateStatusOrder(order_id, orders.getStatus(),staff.getStaff_id());
		ApiResponse res = new ApiResponse();
		HttpStatus http = null;
		if(updateOrders != null) {
			res.setCode(HttpStatus.OK.value());
			res.setMessage("update success");
			res.setStatus(true);
			http = HttpStatus.OK;
		}else {
			res.setCode(HttpStatus.CONFLICT.value());
			res.setMessage("update fail");
			res.setStatus(false);
			http = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<ApiResponse>(res,http) ;
	}
	
	@GetMapping("/all")
	public ResponseEntity<ListEntityStatusResponse> getAllOrder(){
		ListEntityStatusResponse res = new ListEntityStatusResponse();
		HttpStatus http = null;
		List<Orders> allOrder = orderService.getAllOrders();
		if(allOrder.isEmpty()) {
			res.setData(null);
			res.setMessage("not found");
			res.setStatus(HttpStatus.CONFLICT.value());
			http = HttpStatus.CONFLICT;
		}else {
			res.setData(allOrder);
			res.setMessage("find order success");
			res.setStatus(HttpStatus.OK.value());
			http = HttpStatus.OK;
		}
		return new ResponseEntity<ListEntityStatusResponse>(res,http);
	}
	
	@GetMapping("/{orderId}/find")
	public ResponseEntity<EntityStatusResponse> findOrderById(@PathVariable Long orderId) throws ProductException{
		Orders orders = orderService.findOrderByOrderId(orderId);
		EntityStatusResponse res = new EntityStatusResponse(orders,HttpStatus.OK.value(),"find order success");
		return new ResponseEntity<EntityStatusResponse>(res,HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<ListEntityStatusResponse> findOrderByStatus(@RequestParam String status){
		List<Orders> listOrder = orderService.findOrderByStatus(status);
		ListEntityStatusResponse res = new ListEntityStatusResponse<>();
		HttpStatus http = null;
		if(listOrder != null){
			res.setData(listOrder);
			res.setMessage("success");
			res.setStatus(HttpStatus.OK.value());
			http  = HttpStatus.OK;
		}else{
			res.setData(null);
			res.setMessage("not found orders");
			res.setStatus(HttpStatus.CONFLICT.value());
			http = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<>(res,http);
	}

	@GetMapping("/date")
	public ResponseEntity<ListEntityStatusResponse> findListOrderByDateStartAndDateEnd(@RequestParam String start, @RequestParam String end){
			LocalDate dateStart = null;
	        LocalDate dateEnd = null;	       
	            // Parsing a String to Date
	            dateStart = LocalDate.parse(start);
	            dateEnd = LocalDate.parse(end);        
			List<Orders> listOrder = orderService.findOrderByDate(dateStart, dateEnd);
			//List<Orders> listOrder = null;
			ListEntityStatusResponse res = new ListEntityStatusResponse<>();
			HttpStatus http = null;
			if(listOrder != null){
				res.setData(listOrder);
				res.setMessage("success");
				res.setStatus(HttpStatus.OK.value());
				http = HttpStatus.OK;
			}else{
				res.setData(null);
				res.setMessage("not found orders");
				res.setStatus(HttpStatus.CONFLICT.value());
				http = HttpStatus.CONFLICT;
			}
			return new ResponseEntity<>(res,http);
	}
	

}
