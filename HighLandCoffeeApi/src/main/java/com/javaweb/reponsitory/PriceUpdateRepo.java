package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.PriceUpdateDetail;

@Repository
public interface PriceUpdateRepo extends JpaRepository<PriceUpdateDetail, Long>{
	@Query(value = "SELECT * FROM price_update_detail WHERE product_id = ?1  ", nativeQuery = true)
	PriceUpdateDetail findPriceByProductId(String product_id);

	

}
