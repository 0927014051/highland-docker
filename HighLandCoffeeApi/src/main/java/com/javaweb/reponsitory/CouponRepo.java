package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.Coupon;

@Repository
public interface CouponRepo extends JpaRepository<Coupon,Long> {

}
