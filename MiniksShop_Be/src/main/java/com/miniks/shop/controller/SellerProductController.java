package com.miniks.shop.controller;

import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.exception.ProductException;
import com.miniks.shop.exception.SellerException;
import com.miniks.shop.request.CreateProductRequest;
import com.miniks.shop.service.ProductService;
import com.miniks.shop.service.SellerService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers/products")
public class SellerProductController {

    private final SellerService sellerService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProductBySellerHandler(
            @RequestHeader("Authorization") String jwtToken) throws SellerException {

        Seller seller = sellerService.getSellerProfile(jwtToken);

        List<Product> products = productService.getProductsBySellerId(seller.getId());

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> createProductHandler(
            @RequestBody CreateProductRequest request,
            @RequestHeader("Authorization") String jwtToken
    ) throws ExecutionControl.UserException, ProductException, SellerException {

        Seller seller = sellerService.getSellerProfile(jwtToken);

        Product product = productService.createProduct(request, seller);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductHandler(@PathVariable Long productId) {

        try {
            productService.deleteProduct(productId);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (ProductException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProductHandler(
            @PathVariable Long productId, @RequestBody Product product
    ) throws ProductException {

//        try {
        Product updatedProduct = productService.updateProduct(productId, product);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

//        } catch (ProductException e) {
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

}
