package com.miniks.shop.service;

import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductService {

    Product createProduct(CreateProductRequest request, Seller seller);

    void deleteProduct(Long productId);

    Product updateProduct(Long productId, Product product);

    Product findProductById(Long productId);

    List<Product> searchProduct();

    Page<Product> getAllProducts(
            String category,
            String brand,
            String color,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber
    );

    List<Product> getProductsBySellerId(Long sellerId);

}
