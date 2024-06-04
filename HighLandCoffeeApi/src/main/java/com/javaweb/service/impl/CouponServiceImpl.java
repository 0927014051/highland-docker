package com.javaweb.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaweb.entity.Coupon;
import com.javaweb.entity.CouponDetail;
import com.javaweb.exception.ProductException;
import com.javaweb.reponsitory.CouponDetailRepo;
import com.javaweb.reponsitory.CouponRepo;
import com.javaweb.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{

    private CouponRepo couponRepo;
    private CouponDetailRepo couponDetailRepo;
    

    public CouponServiceImpl(CouponRepo couponRepo,CouponDetailRepo couponDetailRepo) {
        this.couponRepo = couponRepo;
        this.couponDetailRepo = couponDetailRepo;
    }

    @Override
    public Coupon createCoupon(Coupon coupon,Long staff_id) {
       Coupon createCoupon = new Coupon();
       createCoupon.setContent(coupon.getContent());
       createCoupon.setCreated_at(LocalDateTime.now());
       createCoupon.setCreated_by(staff_id);
       createCoupon.setEnd_date(coupon.getEnd_date());
       createCoupon.setMinimum_value(coupon.getMinimum_value());
       createCoupon.setQuantity(coupon.getQuantity());
       createCoupon.setRemaining_amount(coupon.getQuantity());
       createCoupon.setUse_value(coupon.getUse_value());
       createCoupon.setStart_date(coupon.getStart_date());
       createCoupon.setStatus(coupon.getStatus());
       createCoupon.setImage(coupon.getImage());
       createCoupon.setType(coupon.getType());
       createCoupon.setUpdated_by(staff_id);
       createCoupon.setUpdated_at(LocalDateTime.now());
       Coupon savedCoupon = couponRepo.save(createCoupon);
       if(savedCoupon != null) {
        CouponDetail couponDetail = new CouponDetail();
        couponDetail.setCoupon_id(savedCoupon.getCoupon_id());
        couponDetail.setStatus(coupon.getStatus());
        CouponDetail saveDetail = couponDetailRepo.save(couponDetail);
        if(saveDetail != null){
            System.err.println("Them thanh cong");
        }
       }
       return savedCoupon;
    }

    @Override
    public Coupon updateStatus(Long id, Coupon coupon) throws ProductException {
       Coupon findCoupon = findById(id);
       findCoupon.setStatus(coupon.getStatus());
       return couponRepo.save(findCoupon);
    }

    @Override
    public Coupon findById(Long id) throws ProductException{
        Optional<Coupon> coupon = couponRepo.findById(id);
        if(coupon.isPresent()){
            return coupon.get();
        }
        throw new ProductException("Coupon not found with id " + id);

    }

    @Override
    public List<Coupon> findAll(){
        return couponRepo.findAll();
    }

    @Override
    public Coupon changeQuantityCouponById(Long coupon_id) throws ProductException {
        Coupon coupon = findById(coupon_id);
        coupon.setQuantity(coupon.getRemaining_amount() - 1 );
        if(coupon.getRemaining_amount() == 0){
            coupon.setStatus("Over");
            couponRepo.save(coupon);
        }
        return couponRepo.save(coupon);
        
    }

}
