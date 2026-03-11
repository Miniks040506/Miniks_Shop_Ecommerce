package com.miniks.shop.controller;

import com.miniks.shop.entity.Product;
import com.miniks.shop.exception.ProductException;
import com.miniks.shop.service.ProductService;
import com.miniks.shop.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final SellerService sellerService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductHandler(
            @PathVariable Long productId) throws ProductException {

        Product product = productService.findProductById(productId);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsHandler(
            @RequestParam(required = false) String query) {

        List<Product> products = productService.searchProducts(query);

        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProductsHandler(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minDiscount,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String stock,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ) {

        return new ResponseEntity<>(
                productService.getAllProducts(category, brand, color,
                        size, minPrice, maxPrice, minDiscount,
                        sort, stock, pageNumber),
                HttpStatus.OK
        );
    }

}
