package com.javaweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.Http;
import com.javaweb.entity.Product;
import com.javaweb.exception.ProductException;
import com.javaweb.response.EntityStatusResponse;
import com.javaweb.response.ListEntityStatusResponse;
import com.javaweb.service.ProductService;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductController {
	
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping("/search/products")
	public ResponseEntity<List<Product>> searchProductHandler(@RequestParam String query){
		List<Product> products = productService.searchProduct(query);
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/products/category")
	public ResponseEntity<EntityStatusResponse> findProductByCategory(@RequestParam String name){
		List<Product> products = productService.findProductByCategory(name);
		System.err.println("prodcy" + products);
		EntityStatusResponse res = new EntityStatusResponse<>();
		HttpStatus send = null;
		if(!products.isEmpty()) {
			res.setData(products);
			res.setMessage("find product by category success");
			res.setStatus(HttpStatus.OK.value());
			send = HttpStatus.OK;
		}else {
			res.setData(null);
			res.setMessage("find product by category not found");
			res.setStatus(HttpStatus.CONFLICT.value());
			send = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<EntityStatusResponse>(res,send);
	}

	@RequestMapping("/product/all")
	public ResponseEntity<ListEntityStatusResponse> getAllProduct(){
		List<Product> listProducts = productService.getAllProducts();
		List<Product> allProducts = new ArrayList<>();
		for(Product item : listProducts){
			if(item.getStatus().equals("Active")){
				allProducts.add(item);
			}
		}
		ListEntityStatusResponse res = new ListEntityStatusResponse<>();
		res.setData(allProducts);
		res.setMessage("success");
		res.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
}
