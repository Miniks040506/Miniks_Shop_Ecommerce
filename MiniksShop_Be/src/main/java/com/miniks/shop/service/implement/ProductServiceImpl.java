package com.miniks.shop.service.implement;

import com.miniks.shop.entity.Category;
import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.exception.ProductException;
import com.miniks.shop.repository.CategoryRepository;
import com.miniks.shop.repository.ProductRepository;
import com.miniks.shop.request.CreateProductRequest;
import com.miniks.shop.service.ProductService;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Override
    public Product createProduct(CreateProductRequest request, Seller seller) {

        Category categoryL1 = categoryRepository.findByCategoryId(request.getCategoryL1());
        if (categoryL1 == null) {
            Category category = new Category();
            category.setCategoryId(request.getCategoryL1());
            category.setLevel(1);
            categoryL1 = categoryRepository.save(category);
        }

        Category categoryL2 = categoryRepository.findByCategoryId(request.getCategoryL2());
        if (categoryL2 == null) {
            Category category = new Category();
            category.setCategoryId(request.getCategoryL2());
            category.setLevel(2);
            category.setParentCategory(categoryL1);
            categoryL2 = categoryRepository.save(category);
        }

        Category categoryL3 = categoryRepository.findByCategoryId(request.getCategoryL3());
        if (categoryL3 == null) {
            Category category = new Category();
            category.setCategoryId(request.getCategoryL3());
            category.setLevel(3);
            category.setParentCategory(categoryL2);
            categoryL3 = categoryRepository.save(category);
        }

        int discountPercentage = calculateDiscountPercentage(request.getMrpPrice(), request.getSellingPrice());

        Product product = new Product();
        product.setSeller(seller);
        product.setCategory(categoryL3);
        product.setDescription(request.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        product.setTitle(request.getTitle());
        product.setColor(request.getColor());
        product.setMrpPrice(request.getMrpPrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setImages(request.getImages());
        product.setSizes(request.getSizes());
        product.setDiscountPercent(discountPercentage);

        return productRepository.save(product);
    }

    private int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {

        if (mrpPrice <= 0) {
            throw new IllegalArgumentException("Actual price must be greater than 0.");
        }

        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount/mrpPrice) * 100;

        return (int) discountPercentage;
    }

    @Override
    public void deleteProduct(Long productId) {

        Product product = findProductById(productId);

        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        findProductById(productId);

        product.setId(productId);

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductException("Product not found with id - " + productId));
    }

    @Override
    public List<Product> searchProduct() {
        return List.of();
    }

    @Override
    public Page<Product> getAllProducts(String category, String brand, String color, String sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber) {
        return null;
    }

    @Override
    public List<Product> getProductsBySellerId(Long sellerId) {
        return List.of();
    }
}
