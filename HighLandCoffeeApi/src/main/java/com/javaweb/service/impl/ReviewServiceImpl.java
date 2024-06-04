package com.javaweb.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import com.javaweb.entity.Customer;
import com.javaweb.entity.OrderDetail;
import com.javaweb.entity.Orders;
import com.javaweb.entity.Review;
import com.javaweb.entity.User;
import com.javaweb.exception.ProductException;
import com.javaweb.exception.UserException;
import com.javaweb.reponsitory.ReviewRepo;
import com.javaweb.request.ReviewRequest;
import com.javaweb.service.CustomerService;
import com.javaweb.service.OrderDetailService;
import com.javaweb.service.OrderService;
import com.javaweb.service.ReviewService;
import com.javaweb.service.UserService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private OrderDetailService orderDetailService;
    private ReviewRepo reviewRepo;
    private OrderService orderService;
    private UserService userService;
    private CustomerService customerService;

    

    public ReviewServiceImpl(OrderDetailService orderDetailService, ReviewRepo reviewRepo,OrderService orderService,UserService userService,CustomerService customerService) {
        this.orderDetailService = orderDetailService;
        this.reviewRepo = reviewRepo;
        this.orderService = orderService;
        this.userService = userService;
        this.customerService = customerService;
    }



    @Override
    @Transactional
    public Review createReview(ReviewRequest rq, Long customer_id) throws ProductException, UserException {
        OrderDetail od = new OrderDetail();
        List<OrderDetail> listOd = new ArrayList<>();
        Review rv = new Review();
        Review savedReview = new Review();
        float point = 0;
        int price = 0;
        if (rq.getOrder_id() != null && !rq.getProduct_id().equals("")) {
            od = orderDetailService.findFirstByProductProductIdOrderByOrderDetailIdAsc(rq.getProduct_id());
            listOd = orderDetailService.findOrderDetailByOrderIdAndProductId(rq.getOrder_id(), rq.getProduct_id());
        }
        if (od != null && listOd != null) {
           Orders order = orderService.findOrderByOrderId(od.getOrder_id());
            Customer customer = customerService.findCustomerById(customer_id);
            User user = userService.findUserById(customer.getUser_id());
            for(OrderDetail item : listOd){
                System.err.println("pir = " + item.getPrice());
                price = price + item.getPrice();
            }
            point = (float) (price*0.08);
            System.out.print("price " + price + "    " + "point" + point);
            user.setPoints( (int)point + user.getPoints());
            if (!rq.getContent().equals("")) {
                rv.setContent(rq.getContent());
            }
            if (rq.getStar() != 0) {
                rv.setStar(rq.getStar());
            }
            rv.setOrder_detail_id(od.getOrder_detail_id());
            rv.setCreated_at(LocalDateTime.now());
            rv.setCreated_by(customer_id);
            rv.setProduct_id(rq.getProduct_id());
            rv.setStatus("Active");
            rv.setUpdated_at(LocalDateTime.now());
        }
        savedReview = reviewRepo.save(rv);
        return savedReview;
    }

}
