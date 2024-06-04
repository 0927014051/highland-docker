package com.javaweb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.Http;
import com.javaweb.entity.Customer;
import com.javaweb.entity.Review;
import com.javaweb.entity.Staff;
import com.javaweb.entity.User;
import com.javaweb.exception.UserException;
import com.javaweb.request.ReviewRequest;
import com.javaweb.response.ApiResponse;
import com.javaweb.service.CustomerService;
import com.javaweb.service.ReviewService;
import com.javaweb.service.StaffService;
import com.javaweb.service.UserService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private ReviewService reviewService;
    private UserService userService;
    private CustomerService customerService;



    public ReviewController(ReviewService reviewService, UserService userService, CustomerService customerService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.customerService = customerService;
    }



    @RequestMapping("/add")
    public ResponseEntity<ApiResponse> createReviewCustomer(@RequestHeader("Authorization") String jwt, @RequestBody ReviewRequest rq) throws UserException{
        User user = userService.findUserByJwt(jwt);
        Customer customer = customerService.findCustomerByUserId(user.getUser_id());
        ApiResponse res = new ApiResponse();
        HttpStatus http = null;
        try {
            Review createReview = reviewService.createReview(rq, customer.getCustomer_id());
             res.setCode(HttpStatus.CREATED.value());
             res.setMessage("success");
             res.setStatus(true);
            http = HttpStatus.CREATED;
        } catch (Exception e) {
            res.setCode(HttpStatus.CONFLICT.value());
            res.setMessage(e.getMessage());
            res.setStatus(false);
            http = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(res,http);

    }

}
