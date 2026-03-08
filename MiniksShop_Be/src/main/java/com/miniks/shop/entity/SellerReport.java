package com.miniks.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "seller_report")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SellerReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false, unique = true)
    private Seller seller;

    private Double totalEarnings = 0.0;

    private Long totalSales = 0L;

    private Double totalRefunds = 0.0;

    private Long totalTax = 0L;

    private Double netEarnings = 0.0;

    private Integer totalOrders = 0;

    private Integer canceledOrders = 0;

    private Integer totalTransactions = 0;

}
