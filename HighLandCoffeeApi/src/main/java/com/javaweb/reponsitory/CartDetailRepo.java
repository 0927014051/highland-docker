package com.javaweb.reponsitory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.Cart;
import com.javaweb.entity.CartDetail;
import com.javaweb.entity.Product;

@Repository
public interface CartDetailRepo extends JpaRepository<CartDetail, Long>{
	@Query("SELECT ci From CartDetail ci Where ci.cart=:cart And ci.product=:product And ci.cart.customer_id=:customerId")
	public CartDetail isCartItemExist(@Param("cart")Cart cart,@Param("product")Product product, @Param("customerId")Long customerId);
	
	@Query("SELECT SUM(quantity*price) FROM CartDetail WHERE cart_id = ?1")
	public int totalPriceByCartId(Long cart_id);
	
	@Query("SELECT SUM(quantity) FROM CartDetail WHERE cart_id = ?1")
	public int totalQuantityByCartId(Long cart_id);
	
	@Query( value = "SELECT * FROM cart_detail WHERE cart_id = ?1 AND product_id = ?2 AND size = ?3 AND topping = ?4",nativeQuery =  true)
	public CartDetail findCartDetailByCartIdAndProductIdWithTopping(Long cart_id,String product_id, String size, String topping);
	
	@Query( value = "SELECT * FROM cart_detail WHERE cart_id = ?1 AND product_id = ?2 AND size = ?3",nativeQuery =  true)
	public CartDetail findCartDetailByCartIdAndProductIdWithToppingNull(Long cart_id,String product_id, String size);

	
	@Query( value = "SELECT * FROM cart_detail WHERE cart_id = ?1",nativeQuery =  true)
	public List<CartDetail> findCartDetailByCartId(Long cart_id);
	
	@Modifying
    @Query("DELETE FROM CartDetail cd WHERE cd.cart_id = :cart_id")
	public void deleteCartDetail(Long cart_id);
	
	@Modifying
	@Transactional
    @Query("DELETE FROM CartDetail cd WHERE cd.product_id = :product_id AND cd.size = :size AND cd.cart_id = :cart_id")
	public void deleteItemCartDetail(String product_id, String size,Long cart_id);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE CartDetail SET quantity = (quantity + 1) WHERE cart_id = :cartId AND product_id = :productId AND size = :size")
	void incrementQuantity(@Param("cartId") Long cartId, @Param("productId") String productId, @Param("size") String size);
	
	@Modifying
	@Transactional
	@Query("UPDATE CartDetail SET quantity = (quantity - 1) WHERE cart_id = :cartId AND product_id = :productId AND size = :size")
	void reduceQuantity(@Param("cartId") Long cartId, @Param("productId") String productId, @Param("size") String size);
	
	@Modifying
	@Transactional
	@Query("UPDATE CartDetail SET quantity = :quantity WHERE cart_id = :cartId AND product_id = :productId AND size = :size")
	void updateQuantity(@Param("cartId") Long cartId, @Param("productId") String productId, @Param("size") String size, int quantity);
}
