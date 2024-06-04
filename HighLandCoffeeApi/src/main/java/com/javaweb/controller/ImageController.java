package com.javaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.entity.Product;
import com.javaweb.exception.ProductException;
import com.javaweb.reponsitory.ProductRepo;
import com.javaweb.service.IImageService;
import com.javaweb.service.ProductService;

@RestController
public class ImageController {
	@Autowired
	    IImageService imageService;
	
	private ProductRepo productRepo;
	@Autowired
	ProductService productService;
	

	    public ImageController(ProductRepo productRepo) {
		super();
		this.productRepo = productRepo;
	}



		@PostMapping("file")
	    public ResponseEntity create(@RequestParam(name = "file") MultipartFile[] files) throws ProductException {
			Product product = productService.findProductById("5MBS8O");
	            for (MultipartFile file : files) {

	                try {

	                	
	                    String fileName = imageService.save(file);

	                    String imageUrl = imageService.getImageUrl(fileName);
	                    
	                    product.setImage(fileName);
	                    productRepo.save(product);
	                    // do whatever you want with that	                    
	                } catch (Exception e) {
	                System.err.println(e);
	                }
	            }

	        return ResponseEntity.ok().build();
	    }
}
