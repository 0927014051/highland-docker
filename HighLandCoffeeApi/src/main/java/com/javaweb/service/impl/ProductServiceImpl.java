package com.javaweb.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.javaweb.entity.Category;
import com.javaweb.entity.PriceUpdateDetail;
import com.javaweb.entity.Product;
import com.javaweb.exception.ProductException;
import com.javaweb.reponsitory.CategoryRepo;
import com.javaweb.reponsitory.PriceUpdateRepo;
import com.javaweb.reponsitory.ProductRepo;
import com.javaweb.request.CreateProductRequest;
import com.javaweb.request.ProductSaleRequest;
import com.javaweb.service.ProductService;
import com.javaweb.service.UserService;

import io.jsonwebtoken.io.IOException;

@Service
public class ProductServiceImpl implements ProductService {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int CODE_LENGTH = 6;
	private ProductRepo productRepo;
	private PriceUpdateRepo priceUpdateRepo;
	private CategoryRepo categoryRepo;
	@Autowired
	private ProductService productService;
	private final EntityManager entityManager;

	public ProductServiceImpl(ProductRepo productRepo, PriceUpdateRepo priceUpdateRepo, CategoryRepo categoryRepo,EntityManager entityManager) {
		this.productRepo = productRepo;
		this.priceUpdateRepo = priceUpdateRepo;
		this.categoryRepo = categoryRepo;
		this.entityManager = entityManager;
	}
	

	@Override
	public Product createProduct(CreateProductRequest req) throws ProductException{
		List<Product> listProducts = productRepo.findAll();
		String productIdGeneration = generateProductCode();
		while (isProductCodeExists(listProducts, productIdGeneration)) {
			productIdGeneration = generateProductCode();
		}

		Product checkNameProduct = productRepo.findProductByName(req.getProduct_name());
		Category category = categoryRepo.findCategoryByName(req.getCategory_name());
		Product product = new Product();
		Product savedProduct = new Product();
		if (checkNameProduct == null) {
			product.setProduct_id(productIdGeneration);
			product.setCategory_id(category.getCategory_id());
			product.setCreated_at(LocalDateTime.now());
			product.setDescription(req.getDescription());
			product.setImage(req.getImage());
			product.setProduct_name(req.getProduct_name());
			product.setStatus(req.getStatus());
			product.setUpdated_at(LocalDateTime.now());
			product.setCreated_by(req.getStaff_id());
			product.setUpdated_by(req.getStaff_id());
			savedProduct = productRepo.save(product);
			if (savedProduct != null) {
				PriceUpdateDetail priceUpdateDetail = new PriceUpdateDetail();
				priceUpdateDetail.setCreated_at(LocalDateTime.now());
				priceUpdateDetail.setCreated_by(req.getStaff_id());
				priceUpdateDetail.setUpdated_by(req.getStaff_id());
				priceUpdateDetail.setPrice_new(req.getPrice());
				priceUpdateDetail.setPrice_old(req.getPrice());
				priceUpdateDetail.setProduct_id(savedProduct.getProduct_id());
				priceUpdateDetail.setUpdated_at(LocalDateTime.now());
				PriceUpdateDetail savePriceUpdateDetail = priceUpdateRepo.save(priceUpdateDetail);
			}
		}else {
			throw new ProductException("product name exist ") ;
		}
				
		return savedProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public static boolean isProductCodeExists(List<Product> productList, String productCode) {
		for (Product product : productList) {
			if (product.getProduct_id().equals(productCode)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Product findProductByName(String name) {
		return productRepo.findProductByName(name);
	}

	@Override
	@Transactional
	public Product updateProduct(String productId, CreateProductRequest rq, Long staff_id) throws ProductException {
		
		Product product = findProductById(productId);
		if (!rq.getDescription().equals(null)) {
			product.setDescription(rq.getDescription());
		}
		if (!rq.getImage().equals(null)) {
			product.setImage(rq.getImage());
		}
		if (rq.getCategory_name() != null) {
			Category category = categoryRepo.findCategoryByName(rq.getCategory_name());
			product.setCategory_id(category.getCategory_id());
		}
		if (!rq.getProduct_name().equals(null)) {
			product.setProduct_name(rq.getProduct_name());
		}
		if (staff_id != null) {
			product.setUpdated_by(staff_id);
		}
		if(!rq.getStatus().equals(null)) {
			product.setStatus(rq.getStatus());
		}
		PriceUpdateDetail priceUpdateDetail = priceUpdateRepo.findPriceByProductId(productId);
		priceUpdateDetail.setPrice_old(priceUpdateDetail.getPrice_new());
		if(rq.getPrice() != 0) {
			priceUpdateDetail.setPrice_new(rq.getPrice());
			priceUpdateDetail.setUpdated_by(staff_id);
			priceUpdateDetail.setUpdated_at(LocalDateTime.now());
		}
		Category category = categoryRepo.findCategoryByName(rq.getCategory_name());
		   if (category == null) {
	            // If category doesn't exist, you may choose to throw an exception
	            // or create a new category. For simplicity, let's throw an exception here.
	            throw new ProductException("Category not found");
	        }
		  product.setCategory(category);
		product.setUpdated_at(LocalDateTime.now());
		productRepo.save(product);
		return product;
	}

	@Override
	public Product findProductById(String id) throws ProductException {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			System.err.println("find product" + optionalProduct);
			return optionalProduct.get();
		}
		throw new ProductException("Product not found with id " + id);
	}

	@Override
	public String deleteProduct(String productId, Long staff_id) throws ProductException {
		Product product = findProductById(productId);
		System.err.println("delete product " + product.getProduct_id() + " - " + productId);
		product.setStatus("Unactive");
		product.setUpdated_by(staff_id);
		productRepo.save(product);
		return "Stopped Selling Success";
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		System.err.println("category name --> " + category);
		List<Product> products = productRepo.findByCategory(category);
		return products;
	}

	@Override
	public List<Product> searchProduct(String query){
		List<Product> products = productRepo.searchProduct(query);
		return products;
	}
	
	@Override
	 public List<ProductSaleRequest> getProductSales() {
        List<Object[]> results = productRepo.getProductSales();
        return results.stream()
                .map(this::mapToProductSaleRequest)
                .collect(Collectors.toList());
    }
	private ProductSaleRequest mapToProductSaleRequest(Object[] result) {
        String productId = (String) result[0];
        String productName = (String) result[1];
        long totalSoldQuantity = (long) result[2];
        long totalQuanity = (long) result[3];
        return new ProductSaleRequest(productId, productName, totalSoldQuantity,totalQuanity);
    }
	public static String generateProductCode() {
		Random random = new Random();
		StringBuilder codeBuilder = new StringBuilder();

		for (int i = 0; i < CODE_LENGTH; i++) {
			char randomChar = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
			codeBuilder.append(randomChar);
		}

		return codeBuilder.toString();
	}

}
