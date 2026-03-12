package com.miniks.shop.embedded;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BankDetails {
    private String accountNumber;
    private String accountHolderName;
    private String bankName;
//    private String branchName;
//    private String swiftCode;
    private String bankCode;
}