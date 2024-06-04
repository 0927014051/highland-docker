package com.javaweb.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.OrderDetail;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long>{
	@Query("SELECT SUM(quantity*price) FROM OrderDetail WHERE order_id = ?1")
	public int totalPriceByOrderId(Long cart_id);
	
	@Query("SELECT SUM(quantity) FROM OrderDetail WHERE order_id = ?1")
	public int totalQuantityByOrderId(Long cart_id);

	@Query("SELECT o FROM OrderDetail o WHERE order_id = ?1 AND product_id = ?2")
	List<OrderDetail> findOrderDetailByOrderIdAndProductId(Long order_id,String product_id);

    @Query("SELECT DISTINCT o FROM OrderDetail o WHERE o.order_detail_id = (SELECT MIN(od.order_detail_id) FROM OrderDetail od WHERE od.product.product_id = :product_id) ORDER BY o.order_detail_id ASC")
    OrderDetail findFirstByProductProductIdOrderByOrderDetailIdAsc(String product_id);
}


