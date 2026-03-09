package com.miniks.shop.entity;

import com.miniks.shop.domain.AccountStatus;
import com.miniks.shop.domain.USER_ROLE;
import com.miniks.shop.embedded.BankDetails;
import com.miniks.shop.embedded.BusinessDetails;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "seller")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sellerName;

    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();

    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    private String taxCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private USER_ROLE role = USER_ROLE.ROLE_SELLER;

    @Column(name = "is_email_verified", nullable = false)
    private boolean isEmailVerified = false;

    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;

}