package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.CouponDetail;

public interface CouponDetailService {

    CouponDetail customerGetCoupon(Long customer_id, Long coupon_id);

    CouponDetail findCouponDetailByCouponId(Long id);

    List<CouponDetail> findCouponDetailByCustomerId(Long id);

    
} 
