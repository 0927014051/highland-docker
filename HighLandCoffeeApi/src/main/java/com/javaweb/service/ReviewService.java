package com.javaweb.service;

import com.javaweb.entity.Review;
import com.javaweb.exception.ProductException;
import com.javaweb.exception.UserException;
import com.javaweb.request.ReviewRequest;

public interface ReviewService {

    Review createReview(ReviewRequest rq,Long customer_id) throws ProductException, UserException;

}
