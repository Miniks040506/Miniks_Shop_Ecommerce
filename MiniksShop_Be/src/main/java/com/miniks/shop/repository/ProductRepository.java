package com.miniks.shop.repository;

import com.miniks.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

//    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN FETCH p.category " +
            "JOIN FETCH p.seller " +
            "LEFT JOIN FETCH p.images " +
            "WHERE p.seller.id = :id"
    )
    List<Product> findBySellerId(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN FETCH p.category c " +
            "JOIN FETCH p.seller s " +
            "LEFT JOIN FETCH p.images " +
            "WHERE :query IS NULL OR " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Product> searchProduct(@Param("query") String query);

}
