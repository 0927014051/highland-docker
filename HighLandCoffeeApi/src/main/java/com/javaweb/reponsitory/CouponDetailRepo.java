package com.javaweb.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.CouponDetail;

@Repository
public interface CouponDetailRepo extends JpaRepository<CouponDetail,Long> {

    @Query(value = "SELECT * FROM coupon_detail WHERE coupon_id = ?1  ", nativeQuery = true)
    CouponDetail findCouponDetailByCouponId(Long id);

    @Query(value = "SELECT * FROM coupon_detail WHERE customer_id = ?1  ", nativeQuery = true)
    List<CouponDetail> findCouponDetailByCustomerId(Long id);


}
