package com.miniks.shop.repository;

import com.miniks.shop.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    Seller findByEmail(String email);

}
