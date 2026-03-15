package com.miniks.shop.controller;

import com.miniks.shop.entity.Seller;
import com.miniks.shop.entity.Transaction;
import com.miniks.shop.service.SellerService;
import com.miniks.shop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final SellerService sellerService;

    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>> getSellerTransactionsHandler(
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {

        Seller seller = sellerService.getSellerProfile(jwtToken);

        List<Transaction> transactions = transactionService.getTransactionBySellerId(seller);

        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactionsHandler() {

        List<Transaction> transactions = transactionService.getAllTransactions();

        return ResponseEntity.ok(transactions);
    }

}
