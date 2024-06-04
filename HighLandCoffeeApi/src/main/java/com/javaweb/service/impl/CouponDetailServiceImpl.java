package com.javaweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaweb.entity.Coupon;
import com.javaweb.entity.CouponDetail;
import com.javaweb.reponsitory.CouponDetailRepo;
import com.javaweb.service.CouponDetailService;
import com.javaweb.service.CouponService;

@Service
public class CouponDetailServiceImpl implements CouponDetailService{

    private CouponDetailRepo couponDetailRepo;
    private CouponService couponService;

    public CouponDetailServiceImpl(CouponDetailRepo couponDetailRepo,CouponService couponService) {
        this.couponDetailRepo = couponDetailRepo;
        this.couponService = couponService;
    }

    @Override
    public CouponDetail customerGetCoupon(Long customer_id,Long coupon_id) {
       CouponDetail detail = findCouponDetailByCouponId(coupon_id);
       detail.setCustomer_id(customer_id);
       return couponDetailRepo.save(detail);
    }

    @Override
    public CouponDetail findCouponDetailByCouponId(Long id) {
        return couponDetailRepo.findCouponDetailByCouponId(id);
    }

    @Override
    public List<CouponDetail> findCouponDetailByCustomerId(Long id) {
        // TODO Auto-generated method stub
        return couponDetailRepo.findCouponDetailByCustomerId(id);
    }





}
