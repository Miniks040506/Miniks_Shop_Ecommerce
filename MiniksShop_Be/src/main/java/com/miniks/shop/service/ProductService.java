package com.miniks.shop.service;

import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.exception.ProductException;
import com.miniks.shop.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductService {

    Product createProduct(CreateProductRequest request, Seller seller);

    void deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, Product product) throws ProductException;

    Product findProductById(Long productId) throws ProductException;

    List<Product> searchProducts(String query);

    Page<Product> getAllProducts(
            String category,
            String brand,
            String color,
            String size,
            Long minPrice,
            Long maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber
    );

    List<Product> getProductsBySellerId(Long sellerId);

}
