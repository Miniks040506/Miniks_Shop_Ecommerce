package com.miniks.shop.repository;

import com.miniks.shop.domain.AccountStatus;
import com.miniks.shop.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    Seller findByEmail(String email);

    List<Seller> findByAccountStatus(AccountStatus status);

}
