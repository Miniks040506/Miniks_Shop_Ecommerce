package com.miniks.shop.service.implement;

import com.miniks.shop.entity.Category;
import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.exception.ProductException;
import com.miniks.shop.repository.CategoryRepository;
import com.miniks.shop.repository.ProductRepository;
import com.miniks.shop.request.CreateProductRequest;
import com.miniks.shop.service.ProductService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
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
        double discountPercentage = (discount / mrpPrice) * 100;

        return (int) discountPercentage;
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) throws ProductException {

        Product product = findProductById(productId);

        productRepository.delete(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long productId, Product product) throws ProductException {

        findProductById(productId);

        product.setId(productId);

        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long productId) throws ProductException {

        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductException("Product not found with id - " + productId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProducts(String query) {

        return productRepository.searchProduct(query);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(String category, String brand, String colors,
                                        String sizes, Long minPrice, Long maxPrice,
                                        Integer minDiscount, String sort, String stock,
                                        Integer pageNumber) {

        Specification<Product> specification = (
                (root, query, criteriaBuilder) -> {

                    List<Predicate> predicates = new ArrayList<>();

                    if (category != null) {
//               Join<Product, Category> categoryJoin = root.join("category", JoinType.INNER);
                        Join<Product, Category> categoryJoin = root.join("category");

                        predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"), category));
                    }

                    if (colors != null && !colors.isEmpty()) {
                        predicates.add(criteriaBuilder.equal(root.get("color"), colors));
                    }

                    if (sizes != null && !sizes.isEmpty()) {
                        predicates.add(criteriaBuilder.equal(root.get("sizes"), sizes));
                    }

                    if (minPrice != null) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice));
                    }

                    if (maxPrice != null) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice));
                    }

                    if (minDiscount != null) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercentage"), minDiscount));
                    }

                    if (stock != null) {
                        predicates.add(criteriaBuilder.equal(root.get("stock"), stock));
                    }

                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

                });

        Pageable pageable;

        if (sort != null && !sort.isEmpty()) {
            pageable = switch (sort) {
                case "price_low" -> PageRequest.of(
                        (pageNumber != null) ? pageNumber : 0,
                        10, Sort.by("sellingPrice").ascending());

                case "price_high" -> PageRequest.of((
                                pageNumber != null) ? pageNumber : 0,
                        10, Sort.by("sellingPrice").descending());

                default -> PageRequest.of(
                        (pageNumber != null) ? pageNumber : 0,
                        10, Sort.unsorted());
            };
        } else {
            pageable = PageRequest.of(
                    (pageNumber != null) ? pageNumber : 0,
                    10, Sort.unsorted());
        }

        return productRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsBySellerId(Long sellerId) {

        return productRepository.findBySellerId(sellerId);
    }
}
